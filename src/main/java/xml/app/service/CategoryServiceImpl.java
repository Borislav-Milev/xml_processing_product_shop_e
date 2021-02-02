package xml.app.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xml.app.domain.entity.Category;
import xml.app.domain.model.binding.CategorySeedDto;
import xml.app.domain.model.binding.root.CategorySeedRootDto;
import xml.app.domain.model.view.query3.CategoryByProductCountRootDto;
import xml.app.repository.CategoryRepository;
import xml.app.service.contract.CategoryService;
import xml.app.util.contract.FileIO;
import xml.app.util.contract.ValidatorUtil;
import xml.app.util.contract.XmlParser;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static xml.app.constant.FilePath.CATEGORIES_BY_PRODUCT_COUNT_VIEW;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final XmlParser xmlParser;
    private final FileIO fileIO;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ValidatorUtil validatorUtil,
                               ModelMapper modelMapper, XmlParser xmlParser, FileIO fileIO) {
        this.categoryRepository = categoryRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.fileIO = fileIO;
    }

    @Override
    public void seedCategories(String xmlCategories) {
        if(this.categoryRepository.count() != 0){
            return;
        }
        CategorySeedRootDto categorySeedRootDto = this.xmlParser.parse(CategorySeedRootDto.class, xmlCategories);

        for(CategorySeedDto categorySeedDto : categorySeedRootDto.getCategorySeedDtos()){
            if(this.validatorUtil.ifNotValidPrintViolations(categorySeedDto)){
                return;
            }
            Category category = this.modelMapper.map(categorySeedDto, Category.class);
            System.out.println();
            this.categoryRepository.saveAndFlush(category);
        }
    }

    @Override
    public int getCount() {
        return Math.toIntExact(this.categoryRepository.count());
    }

    @Override
    public Category findCategoryById(Integer id){
        return this.categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Set<Category> getRandomCategories(){
        Set<Category> categories = new HashSet<>();
        Random random = new Random();
        System.out.println();
        for (int i = 0; i < random.nextInt(this.getCount()) + 1; i++) {
            categories.add(this.getRandomCategory());
        }
        return categories;
    }
    @Override
    public Category getRandomCategory(){
        Random random = new Random();
        return this.findCategoryById(random.nextInt(this.getCount()) + 1);
    }


    //Query 3
    @Override
    public String getCategoriesByProductCount() {
        CategoryByProductCountRootDto categoryByProductCountRootDto = new CategoryByProductCountRootDto();
        categoryByProductCountRootDto.getCategoryByProductCountDtos()
                .addAll(this.categoryRepository.categoryByProductCount());
        this.xmlParser.export(categoryByProductCountRootDto, CategoryByProductCountRootDto.class,
                CATEGORIES_BY_PRODUCT_COUNT_VIEW);
        return this.fileIO.readFile(CATEGORIES_BY_PRODUCT_COUNT_VIEW);
    }
}
