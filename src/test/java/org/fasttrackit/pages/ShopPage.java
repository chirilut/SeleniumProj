package org.fasttrackit.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.apache.commons.lang3.RandomStringUtils;
import org.fasttrackit.utils.Constants;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@DefaultUrl("http://qa3.fasttrackit.org:8008/shop")
public class ShopPage extends PageObject {

    @FindBy(css = ".orderby")
    private WebElementFacade sortBy;

    @FindBy(css = ".product")
    private List<WebElementFacade> listOfProducts;

    @FindBy(css = ".product .add_to_cart_button")
    private List<WebElementFacade> listOfProductsCanBeAdded;

    @FindBy(css = "#meta-2 li")
    private List<WebElementFacade> listaMetaShop;

    @FindBy(css = "a[href*='checkout']")
    public WebElementFacade checkoutBtn;

    @FindBy(css = ".woocommerce-Reviews-title")
    public WebElementFacade reviewTitle;

    @FindBy(id = "comment")
    public WebElementFacade commentField;

    @FindBy(css = ".stars a")
    public List<WebElementFacade> starList;

    @FindBy(id = "author")
    public WebElementFacade nameField;

    @FindBy(id = "email")
    public WebElementFacade emailField;

    @FindBy(id = "submit")
    public WebElementFacade submitReviewBtn;

    @FindBy(css = ".comment")
    public List<WebElementFacade> commentsList;

    @FindBy(css = ".wp-post-image")
    public List<WebElementFacade> productImmage;

    @FindBy(css = ".input-text")
    public WebElementFacade inputQuantity;

    @FindBy(css = ".single_add_to_cart_button")
    public WebElementFacade adtokartbtn;

    @FindBy(css = ".menu a[href*=\"shop\"]")
    public WebElementFacade shopMenuBtn;

    @FindBy(css = "a[title*='View your'] span[class*='amount']")
    public WebElementFacade numberOfProductsInCart;


    String randomComment= RandomStringUtils.randomAlphabetic(10);

    public void clickCheckout(){clickOn(checkoutBtn);}

    public void sortByDropDown(String text){
        sortBy.selectByValue(text);
    }

    public void addProductsToCart(){
        for (WebElementFacade product :listOfProducts){
            try {
                if (product.containsElements(By.cssSelector("a[aria-label^='Add']"))){
                    product.find(By.cssSelector("a[aria-label^='Add']")).waitUntilClickable().then().click();
                }
            }catch (NoSuchElementException e){
                e.printStackTrace();
            }
        }
    }

    public void add2ProductsToCart(){
        int counter=0;
        for (WebElementFacade product :listOfProducts){
            try {
                waitABit(500);
                if (product.containsElements(By.cssSelector("a[aria-label^='Add']"))&&counter<2){
                    waitABit(500);
                    product.find(By.cssSelector("a[aria-label^='Add']")).waitUntilClickable().then().click();
                    waitABit(500);
                    counter++;
                }
            }catch (NoSuchElementException e){
                e.printStackTrace();
            }
        }
    }

    public List<String> getTextFromMetaListElementsShop(){
        List<String> textNameListShop= new ArrayList<>();
          for (WebElementFacade metaElement :listaMetaShop){
              String text= metaElement.findBy(By.cssSelector("a[href^='http']")).getText();
              textNameListShop.add(text);
        }          // System.out.println(textNameListShop);
        return textNameListShop;
    }


    public void addReviewToProduct(String prod, String stars){

        for (WebElementFacade product :listOfProducts){
            try {
                if (product.find(By.cssSelector(".woocommerce-loop-product__title")).getText().equals(prod)){
                    product.click();
                    if (reviewTitle.isDisplayed()){
                        commentField.sendKeys(randomComment);
                        find(By.cssSelector(".stars a:nth-child("+stars+")")).click();
                        nameField.sendKeys(randomComment);
                        emailField.sendKeys(Constants.emailTestare);
                        submitReviewBtn.click();
                    }else{continue;}
                }
            }catch (StaleElementReferenceException e){
                e.printStackTrace();
            }
        }
    }

    public boolean verifyReviewPresent(){
        for (WebElementFacade comment :commentsList){
            if (randomComment.equals(comment.findBy(By.cssSelector(".description")).getText())){
                Assert.assertEquals(randomComment,comment.findBy(By.cssSelector(".description")).getText());
            }
        }  return true;
    }

    public boolean checkPriceDescending() {

        int priceFirstProduct = getPriceFromElement(listOfProducts.get(0));
        System.out.println(listOfProducts.get(0));
        int priceLastProduct = getPriceFromElement(listOfProducts.get(listOfProducts.size()-1));
        System.out.println(listOfProducts.get(listOfProducts.size()-1));
        if (priceFirstProduct > priceLastProduct) {
            return true;
        }
        return false;
    }

    private int getPriceFromElement(@NotNull WebElementFacade element){
        String priceAsString = element
                .findBy(By.cssSelector(".amount")).getText()
                .replace(" lei", "")
                .replace(",","")
                .replace(".","");
        System.out.println(priceAsString);
        return  Integer.parseInt(priceAsString);
    }

    public void addProductXQuantityToCart(String quantity){
        for (WebElementFacade product :listOfProducts){
            try {
                if (product.containsElements(By.cssSelector("a[aria-label^='Add']"))){
                    product.find(By.cssSelector(".wp-post-image")).click();
                }
            }catch (StaleElementReferenceException e){
                e.printStackTrace();
            }
        }
        inputQuantity.click();
        inputQuantity.clear();
        inputQuantity.sendKeys(quantity);
    }

    public void addToKart(){
        adtokartbtn.click();
    }

    public void countHowManyProductsAreIntoCart(String quantity){
        int counter=1;
        int produsul=1;
        for (WebElementFacade product :listOfProductsCanBeAdded){
            try {
                if (product.containsElements(By.cssSelector("a[aria-label^='Add']")) & counter <3 ){
            //    if(product.findBy(By.cssSelector(".products li:nth-of-type("+counter+")")).containsElements(By.cssSelector("a[aria-label^='Add']"))&counter <4){
                                //    }

                    if (product.findBy(By.cssSelector(".products li:nth-of-type("+counter+")")).findElement(findBy(".wp-post-image")).isDisplayed()){
                        waitABit(6000);
                        product.find(By.cssSelector(".wp-post-image")).click();
                        inputQuantity.click();
                        inputQuantity.clear();
                        inputQuantity.sendKeys(quantity + counter);
                        counter++;
                        waitABit(6000);
                        shopMenuBtn.click();
                    }
                }
            }catch (StaleElementReferenceException e){
                e.printStackTrace();
            }

        }

    }
//product.findBy(By.cssSelector(".products li:nth-of-type("+produsul+")"))


    public int addXProductsToCart(int x){
        int counter=0;
        for (WebElementFacade product :listOfProducts){
            try {
                waitABit(500);
                if (product.containsElements(By.cssSelector("a[aria-label^='Add']"))&&counter<x){
                    //waitABit(500);
                    product.find(By.cssSelector("a[aria-label^='Add']")).waitUntilClickable().then().click();
                    //waitABit(500);
                    counter++;
                }
            }catch (NoSuchElementException e){
                e.printStackTrace();
            }
        }return counter;
    }

    public int showNrOfProductsInCart(){
        int i;
        int nrOfProducts = 0;
        String nrAsString[] = numberOfProductsInCart.getText()
                .split(" - ");
        for(i=0;i<nrAsString.length;i++){
            String provizoriu= nrAsString[0].replace("(","").replace(")","");
            nrOfProducts=Integer.parseInt(provizoriu);
        }return nrOfProducts;
    }

    public int showPriceOfProductsInCart(){
        int i;
        int price = 0;
        String nrAsString[] = numberOfProductsInCart.getText()
                .split(" - ");
        for(i=0;i<nrAsString.length;i++){
            String provizoriu= nrAsString[1].replace(",00","").replace(" LEI","");
            price=Integer.parseInt(provizoriu);
        }return price;
    }




}
