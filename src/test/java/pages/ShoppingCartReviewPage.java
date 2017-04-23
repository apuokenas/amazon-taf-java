package pages;

import org.openqa.selenium.By;
import org.testng.TestException;
import utils.CommonUtils;

/**
 * Created by Mantas Tumėnas on 2017-04-21.
 */
public class ShoppingCartReviewPage extends CommonUtils {

  private final By PRICE = By.cssSelector("[class='a-color-price hlb-price a-inline-block a-text-bold']");

  public void verifyOnShoppingCartReviewPage() {
    String url = getCurrentURL();
    System.out.println("SHOPPING_CART_REVIEW_PAGE: Verifying that we are on SHOPPING_CART_REVIEW_PAGE...");
    if (!url.contains("view")) {
      throw new TestException("ERROR: We are not on SHOPPING_CART_REVIEW_PAGE, current URL: " + url + "!");
    }
  }

  public String getCartSubtotal() {
    return getElementText(PRICE);
  }

}
