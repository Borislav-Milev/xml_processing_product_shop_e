package xml.app.constant;

public class FilePath {

    private static final String FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\EX1\\";
    private static final String VIEW_FILE_PATH = FILE_PATH + "views\\";

    public static final String CATEGORIES_PATH = FILE_PATH + "categories.xml";
    public static final String PRODUCTS_PATH = FILE_PATH + "products.xml";
    public static final String USERS_PATH = FILE_PATH + "users.xml";

    public static final String PRODUCTS_IN_RANGE_VIEW = VIEW_FILE_PATH + "query1.xml";
    public static final String USERS_SOLD_PRODUCTS_VIEW = VIEW_FILE_PATH + "query2.xml";
    public static final String CATEGORIES_BY_PRODUCT_COUNT_VIEW = VIEW_FILE_PATH + "query3.xml";
    public static final String USERS_AND_PRODUCTS = VIEW_FILE_PATH + "query4.xml";
}
