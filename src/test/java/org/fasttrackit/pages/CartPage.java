package org.fasttrackit.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;


@DefaultUrl("http://qa3.fasttrackit.org:8008/cart")
public class CartPage extends PageObject {

    @FindBy(css = ".cart-empty")
    public WebElementFacade cartEmptyMessage;

    @FindBy(css = ".remove")
    public WebElementFacade removeBtn;

    @FindBy(css = ".checkout-button")
    public WebElementFacade checkoutBtn;

    @FindBy(css = ".woocommerce-cart-form__cart-item")
    public List<WebElementFacade> list;

    @FindBy(css = ".fa-shopping-cart")
    public WebElementFacade cartBtn;

    @FindBy(css = ".qty")
    public WebElementFacade quantityOfProd;



    public void confirmationEmptyCart(){
        Assert.assertEquals(cartEmptyMessage.getText(),"Your cart is currently empty.");
    }

    public void infirmationEmptyCart(){
        Assert.assertTrue(checkoutBtn.isDisplayed());
    }

    public void clickOnAllRemoveBtns1() {
        try {
            for (WebElementFacade product : list){
                if (find(".remove").isDisplayed()){
                    removeBtn.click();
                    waitABit(1000);
                    cartBtn.click();
                }
            }
        }catch (StaleElementReferenceException e){
            e.printStackTrace();
        }
    }

    public void clickOnAllRemoveBtns2() {
        int counter=10;
        int secondarycounter=110;
        try {
            for (WebElementFacade product : list){
                System.out.println(list.size());
                if (product.findBy(By.cssSelector(".remove")).isDisplayed()){
                    secondarycounter++;
                    System.out.println(secondarycounter);
                    removeBtn.click();
                    waitABit(7000);
                    secondarycounter++;
                    System.out.println(secondarycounter);

                    cartBtn.click();
                    waitABit(5000);
                    secondarycounter++;
                    System.out.println(secondarycounter);
                    find(".remove").click();
                    secondarycounter++;
                    System.out.println(secondarycounter);
                    waitABit(5000);
                    cartBtn.click();
                    secondarycounter++;
                    System.out.println(secondarycounter);

                }
            }
        }catch (StaleElementReferenceException e){
            e.printStackTrace();
        }
    }

    public void clickOnAllRemoveBtns3() { // nu sterge toate produsele din cos.
        int counter=0;
        int secondarycounter=100;
        try {
             for (WebElementFacade product : list){
                     if (product.containsElements(By.cssSelector(".remove"))){
                         System.out.println(counter);
                         removeBtn.click();
                         counter++;
                         System.out.println(counter);
                         Actions action = new Actions(getDriver());
                         secondarycounter++;
                         System.out.println(secondarycounter);
                         WebElement element = getDriver().findElement(By.cssSelector(".remove"));
                         action.moveToElement(element).perform();
                         secondarycounter++;
                         System.out.println(secondarycounter);
                         waitABit(4000);

                         removeBtn.click();
                         secondarycounter++;
                         System.out.println(secondarycounter);
                         cartBtn.click();
                         secondarycounter++;
                         System.out.println(secondarycounter);
                     }
             }
        }catch (StaleElementReferenceException e){
            e.printStackTrace();
        }
    }

    public void goToKart(){
        cartBtn.click();
    }

    public String showQuantityOfProductInKart(){
        String valoare= quantityOfProd.getValue();
        return valoare;
    }







}
