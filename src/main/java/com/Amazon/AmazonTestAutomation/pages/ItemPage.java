package com.Amazon.AmazonTestAutomation.pages;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ItemPage extends BasePage
{
    //constructor for Item page
    public ItemPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[@id='nav-search-label-id']")
    private WebElement departmentName;
    @FindBy(xpath = "//input[@id='nav-search-submit-button']")
    private WebElement searchButton;
    @FindBy(xpath = "//input[@id='twotabsearchtextbox']")
    private WebElement searchBox;
    @FindBy(xpath = "//select[@id='searchDropdownBox']")
    private WebElement dropdownBox;
    @FindBy(xpath = "//div[@class='aok-relative']//following::h2")
    private List<WebElement> listOfProduct;

    @FindBy(xpath = "(//div[@id='brandsRefinements']//li)[2]//div")
    private WebElement filterCheckbox;

    @FindBy(xpath = "((//div[@id='brandsRefinements']//li)[2]//span)[1]")
    private WebElement filterText;

    String sectionDropdown = "//select//option[contains(text(),'%s')]";
    String itemTitle ="(//span[contains(text(),'%s')])[1]";
    String addToCartButtonForItem = "(//span[contains(text(),'%s')])[1]//following::button[contains(text(),'Add to cart')][1]";




    //Method to check whether Add Address page is displayed or not
    public String getDepartmentName()
    {
        Assert.isTrue(departmentName.isDisplayed(),"Page Section name not displayed");
        return departmentName.getText();
    }
    public void selectSection(String section)
    {
        wait.withTimeout(Duration.ofSeconds(5));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);",dropdownBox);

        Select dropdowns = new Select(dropdownBox);
        dropdowns.selectByVisibleText(section);
    }
    public void navigateTo_UserDefined_Section(String section)
    {
        selectSection(section);
        searchButton.click();
        logger.info("Selected Section - "+section);
    }
    public boolean verifyNavigation_To_UserDefined_Section(String section)
    {
        String actualDeptName = getDepartmentName();
        logger.info("Verifying landing on the "+section+" page");
        return actualDeptName.contains(section);
    }
    public void searchItemWithCategory(String item, String category)
    {
        selectSection(category);
        searchBox.clear();
        searchBox.sendKeys(item.toUpperCase());
        searchButton.click();
        logger.info("Searched Item "+item+" from Category - "+category);
    }
    public void searchItem(String item)
    {
        searchBox.clear();
        searchBox.sendKeys(item.toUpperCase());
        searchButton.click();
        logger.info("Searching Item -"+item);
    }
    public String addItemToCart(String item, String category)
    {
        searchItemWithCategory(item,category);
        driver.findElement(By.xpath(String.format(addToCartButtonForItem,item))).click();
        logger.info("Add to Item is clicked for Item - "+item);
        String titleName = driver.findElement(By.xpath(String.format(itemTitle,item))).getText();

        //Wait for 2 seconds
        try{
            f_wait.until(driver -> false);}
        catch (Exception e) {}

        return titleName;
    }

    public String selectFilter()
    {
        //Wait for 2 seconds
        try{
            f_wait.until(driver -> false);}
        catch (Exception e) {}
        filterCheckbox.click();
        logger.info("Filter is selected from the list of Filter");
        String filterName = filterText.getText();
        logger.info("Filter name is fetched");
        return filterName;
    }
    public boolean validateProductsDisplayed(String filter)
    {
        int flag=0;
        //Wait for 2 seconds
        try{
            f_wait.until(driver -> false);}
        catch (Exception e) {}

        List<String> listOfTitle = new ArrayList<>();
        for(WebElement product : listOfProduct)
        {
            listOfTitle.add(product.getText());
        }
        for(int i=0;i<3;i++)
        {
            String name = listOfTitle.get(i).substring(0,30);
            if(name.contains(filter))
                flag=0;
            else
                flag=1;
        }
        if(flag==0)
            return true;
        else
            return false;
    }
}
