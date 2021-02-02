package xml.app.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xml.app.domain.entity.Product;
import xml.app.domain.entity.User;
import xml.app.domain.model.binding.ProductSeedDto;
import xml.app.domain.model.binding.root.ProductSeedRootDto;
import xml.app.domain.model.view.query1.ProductInRangeDto;
import xml.app.domain.model.view.query1.ProductsInRangeRootDto;
import xml.app.repository.ProductRepository;
import xml.app.service.contract.CategoryService;
import xml.app.service.contract.ProductService;
import xml.app.service.contract.UserService;
import xml.app.util.contract.FileIO;
import xml.app.util.contract.ValidatorUtil;
import xml.app.util.contract.XmlParser;

import java.math.BigDecimal;
import java.util.Set;

import static xml.app.constant.FilePath.PRODUCTS_IN_RANGE_VIEW;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final FileIO fileIO;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService,
                              UserService userService, ValidatorUtil validatorUtil,
                              ModelMapper modelMapper, XmlParser xmlParser, FileIO fileIO) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.userService = userService;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.fileIO = fileIO;
    }

    @Transactional
    @Override
    public void seedProducts(String xmlProducts) {
        if (this.productRepository.count() != 0) {
            return;
        }
        ProductSeedRootDto productSeedRootDto = this.xmlParser.parse(ProductSeedRootDto.class, xmlProducts);

        for (ProductSeedDto productSeedDto : productSeedRootDto.getProductSeedDtos()) {
            if (this.validatorUtil.ifNotValidPrintViolations(productSeedDto)) {
                return;
            }

            Product product = this.modelMapper.map(productSeedDto, Product.class);
            product.getCategories().addAll(this.categoryService.getRandomCategories());
            User buyer = this.userService.getRandomUser();
            User seller = this.userService.getRandomUser();
            if (buyer.getId() % 7 != 0 && !buyer.getId().equals(seller.getId())) {
                product.setBuyer(buyer);
            }
            product.setSeller(seller);
            this.productRepository.saveAndFlush(product);
        }
    }

    //Query 1
    @Override
    @Transactional
    public String productsInRange() {
        Set<Product> products = this.productRepository
                .findAllByPriceBetweenAndBuyerOrderByPrice(
                        BigDecimal.valueOf(500), BigDecimal.valueOf(1000), null);

        ProductsInRangeRootDto productsInRangeRootDto = new ProductsInRangeRootDto();
        for (Product product : products) {
            ProductInRangeDto productInRangeDto = this.modelMapper.map(product, ProductInRangeDto.class);

            if(null == product.getSeller().getFirstName()) {
                productInRangeDto.setSeller(product.getSeller().getLastName());
            }else productInRangeDto.setSeller(
                    product.getSeller().getFirstName() + " " + product.getSeller().getLastName());
            productsInRangeRootDto.getProductInRangeDtos().add(productInRangeDto);
        }
        this.xmlParser.export(productsInRangeRootDto, ProductsInRangeRootDto.class, PRODUCTS_IN_RANGE_VIEW);
        return this.fileIO.readFile(PRODUCTS_IN_RANGE_VIEW);
    }
}
