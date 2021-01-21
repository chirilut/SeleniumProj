package org.fasttrackit.features;

import net.thucydides.core.annotations.Steps;
import org.fasttrackit.steps.CartSteps;
import org.fasttrackit.steps.LoginSteps;
import org.fasttrackit.steps.ShopSteps;
import org.fasttrackit.utils.BaseTest;
import org.fasttrackit.utils.Constants;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CartTest extends BaseTest {

    @Steps
    private CartSteps cartSteps;
    @Steps
    private ShopSteps shopSteps;
    @Steps
    private LoginSteps loginSteps;

//TEST 1  -------------------------------------
    @Test // verifica daca cosul este gol
    public void checkIfCartEmpty(){
        cartSteps.verifyCartEmpty();
    }

//TEST 2 -------------------------------------------------------------------------------
    @Test // adauga produse in cos apoi goleste cosul si verifica daca cosul este gol
    public void emptyTheCart(){
        shopSteps.addProductsToCart();
        cartSteps.clickCartBtn();
        cartSteps.removeProductsFromCart();
        cartSteps.verifyCartEmpty();
    }

//TEST 3 -------------------------------------------------------------------------------
    @Test // adauga produse in cos si verific daca sunt prezente dupa logare
    public void checkProductsAreInCartAfterLogin(){
        shopSteps.addProductsToCart();
        loginSteps.navigateToLoginPage();
        loginSteps.setCredentials(Constants.emailTestare,Constants.passTestare);
        loginSteps.clickLogin();
        cartSteps.verifyCartNotEmpty();
    }

//TEST 4 --------------------------------------------------------------------------------
    @Test // compara pretul produselor afisat pe pagina de shop, cu pretul final total
    public void checkProductsPriceIsRight(){
        cartSteps.comparePrices();
    }

//TEST 5 --------------------------------------------------------------------------------
    @Test  // verifica daca pretul produselor adaugate in cos , este corect.
    public void verifyProductsPrice(){
        cartSteps.testProducts();
    }

//TEST 6 ---------------------------------------------------------------------------------
    @Test  // verifica daca nr produselor adaugate in cos , se regaseste.
    public void countAllProductsInCart(){
        cartSteps.countHowManyProductsIntoKart(3);
    }



}
