package com.Amazon.AmazonTestAutomation.utils;

import com.Amazon.AmazonTestAutomation.config.Config;
import java.io.IOException;

public class ExcelHelper {

    ExcelReader excelReader;

    public ExcelHelper() throws IOException {
        try
        {
            excelReader = new ExcelReader(Config.UI_EXCEL_PATH);
            Config.Tests method = Config.Tests.ADD_TO_CART_TEST;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
        Reading Account Credentials from Excel File
        Email ID and Password is saved in excel
    */
    public String getAccountEmailFromExcel() {
        excelReader.selectSheet("Account Details");
        String email = excelReader.getData("EMAIL ID", "CREDENTIALS");
        return email;
    }
    public String getAccountPasswordFromExcel() {
        excelReader.selectSheet("Account Details");
        String password = excelReader.getData("PASSWORD", "CREDENTIALS");
        return password;
    }

    /*
        Reading Section Name from Excel File
    */
    public String getSectionNameFromExcel() {
        excelReader.selectSheet(Config.Tests.SEARCH_CATEGORY_TEST.getTestName());
        String section = excelReader.getData("SECTION_NAME", "USERDATA");
        return section;
    }

    /*
        Reading Count, Section Name and Product Name from Excel File
    */
    public String getCountOfSearchDataFromExcel() {
        excelReader.selectSheet(Config.Tests.ADD_TO_CART_TEST.getTestName());
        String count = excelReader.getData("COUNT", "COUNT_OF_DATA");
        return count;
    }
    public String getSectionNameFromExcel(int no) {
        excelReader.selectSheet(Config.Tests.ADD_TO_CART_TEST.getTestName());
        String section = excelReader.getData("SECTION_NAME", "USERDATA"+String.valueOf(no));
        return section;
    }
    public String getProductNameFromExcel(int no) {
        excelReader.selectSheet(Config.Tests.ADD_TO_CART_TEST.getTestName());
        String product = excelReader.getData("PRODUCT_NAME", "USERDATA"+String.valueOf(no));
        return product;
    }

    /*
        Reading Data To Search from Excel File
    */
    public String getSearchDataFromExcel() {
        excelReader.selectSheet(Config.Tests.SEARCH_FILTER_TEST.getTestName());
        String search = excelReader.getData("SEARCH", "USERDATA");
        return search;
    }

    /*
        Reading below Address Details from Excel File
        Full Name, Mobile No, Pincode, House Details, Area Details, Landmark, Town, State
    */
    public String getFullNameFromExcel() {
        excelReader.selectSheet(Config.Tests.ADDRESS_PAGE_TEST.getTestName());
        String fullName = excelReader.getData("FULL_NAME", "ADDRESS");
        return fullName;
    }
    public String getMobileNoFromExcel() {
        excelReader.selectSheet(Config.Tests.ADDRESS_PAGE_TEST.getTestName());
        String mobileNo = excelReader.getData("MOBILE_NO", "ADDRESS");
        return mobileNo;
    }
    public String getPincodeFromExcel() {
        excelReader.selectSheet(Config.Tests.ADDRESS_PAGE_TEST.getTestName());
        String pinCode = excelReader.getData("PINCODE", "ADDRESS");
        return pinCode;
    }
    public String getHouseDetailsFromExcel() {
        excelReader.selectSheet(Config.Tests.ADDRESS_PAGE_TEST.getTestName());
        String house = excelReader.getData("HOUSE_DETAILS", "ADDRESS");
        return house;
    }
    public String getAreaDetailsFromExcel() {
        excelReader.selectSheet(Config.Tests.ADDRESS_PAGE_TEST.getTestName());
        String area = excelReader.getData("AREA_DETAILS", "ADDRESS");
        return area;
    }
    public String getLandmarkDetailsFromExcel() {
        excelReader.selectSheet(Config.Tests.ADDRESS_PAGE_TEST.getTestName());
        String landmark = excelReader.getData("LANDMARK", "ADDRESS");
        return landmark;
    }
    public String getTownNameFromExcel() {
        excelReader.selectSheet(Config.Tests.ADDRESS_PAGE_TEST.getTestName());
        String town = excelReader.getData("TOWN", "ADDRESS");
        return town;
    }
    public String getStateNameFromExcel() {
        excelReader.selectSheet(Config.Tests.ADDRESS_PAGE_TEST.getTestName());
        String state = excelReader.getData("STATE", "ADDRESS");
        return state;
    }

    /*
        Reading Best Seller Category and Rank from Excel File
    */
    public String getBestSellerCategoryFromExcel() {
        excelReader.selectSheet(Config.Tests.BESTSELLER_PAGE_TEST.getTestName());
        String category = excelReader.getData("BEST_SELLER_CATEGORY", "INPUT");
        return category;
    }
    public String getRankFromExcel() {
        excelReader.selectSheet(Config.Tests.BESTSELLER_PAGE_TEST.getTestName());
        String rank = excelReader.getData("RANK", "INPUT");
        return rank;
    }
}
