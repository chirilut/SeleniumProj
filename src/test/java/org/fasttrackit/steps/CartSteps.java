package org.fasttrackit.steps;

import net.thucydides.core.annotations.Step;
import org.fasttrackit.pages.CartPage;
import org.fasttrackit.pages.CheckoutPage;
import org.fasttrackit.pages.HomePage;
import org.fasttrackit.pages.ShopPage;
import org.junit.Assert;


public class CartSteps {

    private CartPage cartPage;
    private HomePage homePage;
    private ShopPage shopPage;
    private CheckoutPage checkoutPage;


    @Step
    public void clickCartBtn(){
        homePage.clickCartButton();
    }

    @Step
    public void verifyCartEmpty(){
        cartPage.open();
        cartPage.confirmationEmptyCart();
    }

    @Step
    public void verifyCartNotEmpty(){
        cartPage.open();
        cartPage.infirmationEmptyCart();
    }

    @Step
    public void removeProductsFromCart(){
        cartPage.clickOnAllRemoveBtns1();
    }

    @Step  // test 4 compara pretul produselor afisat pe pagina de shop, cu pretul final total
    public void comparePrices(){
        shopPage.open();
        shopPage.add2ProductsToCart();
        shopPage.clickCheckout();
        Assert.assertEquals(shopPage.showPriceOfProductsInCart(),checkoutPage.finalPrice());
    }

    @Step //testam pretul produselor din cos pt test 5
    public void testProducts(){
        shopPage.open();
        shopPage.add2ProductsToCart();
        //shopPage.addXProductsToCart(3);
        shopPage.clickCheckout();
        checkoutPage.cartProductsTotalPrice();
        checkoutPage.finalPrice();
        Assert.assertEquals(checkoutPage.cartProductsTotalPrice(),checkoutPage.finalPrice());
    }

    @Step
    public void countHowManyProductsIntoKart(int x){
        shopPage.open();
        shopPage.addXProductsToCart(x);
        shopPage.showNrOfProductsInCart();
        Assert.assertEquals(x,shopPage.showNrOfProductsInCart());
    }


}
