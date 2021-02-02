package xml.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xml.app.domain.entity.Product;
import xml.app.domain.entity.User;
import xml.app.domain.model.binding.UserSeedDto;
import xml.app.domain.model.binding.root.UserSeedRootDto;
import xml.app.domain.model.view.BaseProductDto;
import xml.app.domain.model.view.query2.ProductWithBuyerDto;
import xml.app.domain.model.view.query2.ProductWithBuyerRootDto;
import xml.app.domain.model.view.query2.UserWithProductsRootDto;
import xml.app.domain.model.view.query2.UserWithProductsDto;
import xml.app.domain.model.view.query4.SoldProductsDto;
import xml.app.domain.model.view.query4.UserWithSoldProductsDto;
import xml.app.domain.model.view.query4.UserWithTotalProductsSoldDto;
import xml.app.repository.UserRepository;
import xml.app.service.contract.UserService;
import xml.app.util.contract.FileIO;
import xml.app.util.contract.ValidatorUtil;
import xml.app.util.contract.XmlParser;

import java.util.*;

import static xml.app.constant.FilePath.USERS_AND_PRODUCTS;
import static xml.app.constant.FilePath.USERS_SOLD_PRODUCTS_VIEW;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final FileIO fileIO;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ValidatorUtil validatorUtil,
                           ModelMapper modelMapper, XmlParser xmlParser, FileIO fileIO) {
        this.userRepository = userRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.fileIO = fileIO;
    }

    @Transactional
    @Override
    public void seedUser(String xmlUsers) {
        if (this.userRepository.count() != 0) {
            return;
        }
        UserSeedRootDto userSeedRootDto = this.xmlParser.parse(UserSeedRootDto.class, xmlUsers);

        for (UserSeedDto userSeedDto : userSeedRootDto.getUserSeedDtos()) {
            if (this.validatorUtil.ifNotValidPrintViolations(userSeedDto)) {
                return;
            }
            User user = this.modelMapper.map(userSeedDto, User.class);
            this.userRepository.saveAndFlush(user);
        }

        this.addFriends();
    }

    private void addFriends() {
        for (int i = 1; i <= this.getCount(); i++) {
            User user = this.findUserById(i);
            Set<User> friends = this.getRandomUsers();
            for (User friend : friends) {
                if (!user.getId().equals(friend.getId())) {
                    user.getFriends().add(friend);
                }
            }
            this.userRepository.saveAndFlush(user);
        }
    }


    @Override
    public int getCount() {
        return Math.toIntExact(this.userRepository.count());
    }

    @Override
    public User findUserById(Integer id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public Set<User> getRandomUsers() {
        Set<User> friends = new HashSet<>();
        Random random = new Random();
        for (int i = 0; i < random.nextInt(this.getCount()) + 1; i++) {
            friends.add(this.getRandomUser());
        }
        return friends;
    }

    @Override
    public User getRandomUser() {
        Random random = new Random();
        return this.findUserById(random.nextInt(this.getCount()) + 1);
    }

    //Query 2
    @Override
    @Transactional
    public String soldProducts() {
        List<User> sellers = this.userRepository.findAllSellersWithItemSold();
        UserWithProductsRootDto userWithProductsRootDto = new UserWithProductsRootDto();


        for (User seller: sellers) {
            System.out.println();
            ProductWithBuyerRootDto productWithBuyerRootDto = new ProductWithBuyerRootDto();
            for (Product product : seller.getProductsSold()) {

                if(null == product.getBuyer()) continue;
                ProductWithBuyerDto productWithBuyerDto = this.modelMapper.map(product, ProductWithBuyerDto.class);
                productWithBuyerDto.setFirstName(product.getBuyer().getFirstName());
                productWithBuyerDto.setLastName(product.getBuyer().getLastName());

                productWithBuyerRootDto.getProductWithBuyerDtos()
                        .add(productWithBuyerDto);
            }
            if(productWithBuyerRootDto.getProductWithBuyerDtos().size() == 0){
                continue;
            }
            UserWithProductsDto userWithProductsDto = this.modelMapper.map(seller, UserWithProductsDto.class);
            userWithProductsDto.setProductWithBuyerRootDto(productWithBuyerRootDto);

            userWithProductsRootDto.getUserWithProductsDtos().add(userWithProductsDto);
        }
        System.out.println();




        this.xmlParser.export(userWithProductsRootDto, UserWithProductsRootDto.class, USERS_SOLD_PRODUCTS_VIEW);
        return this.fileIO.readFile(USERS_SOLD_PRODUCTS_VIEW);
    }


    //Query 4
    @Override
    @Transactional
    public String usersWithAtLeastOneSoldProduct() {
        UserWithTotalProductsSoldDto userWithTotalProductsSoldDto = new UserWithTotalProductsSoldDto();
        List<User> sellers = this.userRepository.countOfSellers();
        for (User seller : sellers) {
            SoldProductsDto soldProductsDto = new SoldProductsDto();
            int counter = 0;
            for (Product product : seller.getProductsSold()) {
                if(null == product.getBuyer()) continue;
                soldProductsDto.getBaseProductDtoList().add(this.modelMapper.map(product, BaseProductDto.class));
                counter++;
            }
            if(counter == 0) continue;
            soldProductsDto.setCount(counter);
            UserWithSoldProductsDto userWithSoldProductsDto = this.modelMapper.map(seller, UserWithSoldProductsDto.class);
            System.out.println();
            userWithSoldProductsDto.setSoldProductsDto(soldProductsDto);
            userWithTotalProductsSoldDto.getUserWithSoldProductsDtos().add(userWithSoldProductsDto);

        }
        userWithTotalProductsSoldDto.setCount(userWithTotalProductsSoldDto.getUserWithSoldProductsDtos().size());
        System.out.println();
        this.xmlParser.export(userWithTotalProductsSoldDto, UserWithTotalProductsSoldDto.class, USERS_AND_PRODUCTS);
        return this.fileIO.readFile(USERS_AND_PRODUCTS);
    }


}
