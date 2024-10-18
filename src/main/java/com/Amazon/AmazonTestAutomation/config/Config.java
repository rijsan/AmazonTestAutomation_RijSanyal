package com.Amazon.AmazonTestAutomation.config;

public class Config {

    public static final String UI_EXCEL_PATH = "src/main/resources/inputData.xlsx";
    public static final String AmazonURL = "https://www.amazon.in";
    public static final String ScreenshotFolderPath = "src/test/resources/reports/screenshots/";

    public enum Tests {
        SEARCH_CATEGORY_TEST("Validate_OpenCategory"),
        ADD_TO_CART_TEST("Validate_AddToCartFeature"),
        SEARCH_FILTER_TEST("Validate_SearchAndFilter"),
        ADDRESS_PAGE_TEST("Validate_Amazon_AddAddress"),
        BESTSELLER_PAGE_TEST("Validate_BestSellerPage");

        private final String TestName;

        Tests(String TestName) {
            this.TestName = TestName;
        }

        public String getTestName() {
            return TestName;
        }
    }
}
