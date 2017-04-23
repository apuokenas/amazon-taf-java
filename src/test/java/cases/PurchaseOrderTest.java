package cases;

import actions.*;
import base.LoadProperties;
import enums.Products;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ShoppingCartReviewPage;
import pojo.Book;
import utils.DriverUtils;

import static org.testng.Assert.assertEquals;

/**
 * Created by Mantas Tumėnas on 2017-04-21.
 */
public class PurchaseOrderTest {

  public WebDriver driver;
  Products testBook = Products.ISTQB_FOUNDATION;
  String username = LoadProperties.user.getProperty("tester.username");
  String password = LoadProperties.user.getProperty("tester.password");
  ShoppingActions shoppingActions = new ShoppingActions();
  ShoppingCartReviewPage shoppingCartReviewPage = new ShoppingCartReviewPage();

  @BeforeClass
  public void setUp() {
    driver = DriverUtils.getDriver();
  }

  @Test(description="Launches the French Amazon site", priority = 1)
  public void testLaunchSite() {
    shoppingActions.initializeLogin();
    shoppingActions.navigateToHomePage();
  }

  @Test(description="Logs in", priority = 2)
  public void testLogIn() {
    shoppingActions.logInAs(username, password);
  }

  @Test(description = "Navigates to the shopping cart and makes sure it is empty", priority = 3)
  public void testInitializeShoppingCart() {
    shoppingActions.prepareCart();
  }

  @Test(description = "Adds one item to the shopping cart", priority = 4)
  public void testAddProductToShoppingCart() {
    shoppingActions.addProductToShoppingCartReview(testBook);
  }

  @Test(description = "Compares the cart subtotal versus the item price", priority = 5)
  public void testVerifyPrices() {
    String actualCartSubtotalPrice = shoppingCartReviewPage.getCartSubtotal();
    Book bookProductPage = shoppingActions.loadProductPageDataIntoProductObject(testBook);
    String expectedBookPrice = bookProductPage.getOfferPrice();
    shoppingActions.checkMatchingValues("*** Verifying the actual price listed for the book: ***",
      actualCartSubtotalPrice, expectedBookPrice);
    assertEquals(actualCartSubtotalPrice, expectedBookPrice,
      "ERROR in SHOPPING_CART_REVIEW: The shopping cart subtotal is not what was expected!");
  }

  @AfterClass
  public void tearDown() {
    driver.quit();
  }

}
