package org.fasttrackit.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.ArrayList;
import java.util.List;


@DefaultUrl("http://qa3.fasttrackit.org:8008/checkout")
public class CheckoutPage extends PageObject {

    @FindBy(css = ".cart_item")
    private List <WebElementFacade> checkoutProducts;



    public int cartProductsTotalPrice(){  // test 5
                    int counter = 0;
                    int provizoriu=0;
                    int sum=0;
                    List <Integer> listaDeStringuri = new ArrayList<>();
                for (WebElementFacade produs : checkoutProducts) {
                    try {
                        if(produs.isDisplayed()) {
                            String pret = produs.findElement(By.cssSelector(".cart_item .amount")).getText().replace(",00 lei", "");
                            provizoriu=Integer.parseInt(pret);
                            counter++;
                            listaDeStringuri.add(provizoriu);
                        }
                    }catch (StaleElementReferenceException er){
                            er.printStackTrace();
                    }
                }//System.out.println(listaDeStringuri);

        for (int i=0; i<listaDeStringuri.size();i++){
            sum=sum+listaDeStringuri.get(i);
        }//System.out.println(sum);
        return sum;
    }

    public int finalPrice(){
        String total =findBy(".cart-subtotal .woocommerce-Price-amount").getText().replace(",00 lei","");
        int pretFinal= Integer.parseInt(total);
        //System.out.println(pretFinal);
        return pretFinal;
    }





}
