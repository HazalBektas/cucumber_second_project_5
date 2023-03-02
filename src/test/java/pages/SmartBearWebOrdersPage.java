package pages;

import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SmartBearWebOrdersPage extends SmartBearLoginPage {
    public SmartBearWebOrdersPage() {
        super();
    }

    @FindBy(css = "ul[id=ctl00_menu]>li")
    public List<WebElement> menuItemsList;

    @FindBy(id = "ctl00_MainContent_btnCheckAll")
    public WebElement checkAllBtn;

    @FindBy(css = "p[class='CheckUncheck']")
    public List<WebElement> checkUnCheckBtn;

    @FindBy(id = "ctl00_MainContent_btnUncheckAll")
    public WebElement unCheckButton;

    @FindBy(css = "table[class='SampleTable']")
    public WebElement ordersTable;


    @FindBy(css = "table[id=ctl00_MainContent_fmwOrder_cardList] label")
    public List<WebElement> orderPmntCardType;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox6")
    public WebElement orderPmntCardNO;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox1")
    public WebElement orderPmntCardExp;

    @FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
    public WebElement btnProcessOrder;

    @FindBy(tagName = "h2")
    public WebElement subHeading;

    @FindBy(id = "ctl00_MainContent_orderMessage")
    public WebElement msgEmptyOrder;

    @FindBy(id = "ctl00_MainContent_btnDelete")
    public WebElement btnDeleteSelected;



    public void clickByVisibleTextBtn(String btn) {
        switch (btn) {
            case "Check All":
                checkAllBtn.click();
                break;
            case "Uncheck All":
                unCheckButton.click();
                break;
            case "Process":
                btnProcessOrder.click();
                break;
            case "Delete Selected":
                btnDeleteSelected.click();
                break;
            default:
                throw new NotFoundException("'"+btn+"'" + " button not found");
        }

    }

    public void selectMenuItemByText(String option) {
        for (WebElement element : menuItemsList) {
            if (element.getText().equals(option)) {
                element.click();
                break;
            }
        }
    }

    @FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
    public WebElement productOptions;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
    public WebElement productQuantity;

    @FindBy(xpath = "((//table)[2]//ol)[2]//input")
    public List<WebElement> orderAddressInfoInputList;



    public void selectCardType(String card) {
        switch (card) {
            case "Visa":
                orderPmntCardType.get(0).click();
                break;
            case "MasterCard":
                orderPmntCardType.get(1).click();
                break;
            case "American Express":
                orderPmntCardType.get(2).click();
                break;
            default:
                throw new NotFoundException(card + " is not available!");
        }
    }
}