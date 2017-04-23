package pages;

import enums.Url;
import org.openqa.selenium.By;
import utils.CommonUtils;

/**
 * Created by Mantas Tumėnas on 2017-04-21.
 */
public class HomePage extends CommonUtils {

  private final By YOUR_ACCOUNT = By.id("nav-link-yourAccount");
  private final By SHOPPING_CART_ICON = By.cssSelector("#nav-cart");
  private final By SHOPPING_CART_COUNT = By.cssSelector("#nav-cart > #nav-cart-count");

  public HomePage() {
  }

  public void signOutWithSignOutLink() {
    String url = Url.BASE_URL.getURL() + Url.SIGNOUT.getURL();
    navigateToURL(url);
  }

  public void navigateToHomePage() {
    String url = Url.BASE_URL.getURL();
    System.out.println("INITIALIZING: Navigating to Amazon.fr: " + url + "...\n");
    navigateToURL(url);
  }

  public void navigateToSignInPage() {
    System.out.println("HOME_PAGE: Selecting [YOUR_ACCOUNT] in navigation bar.");
    scrollToThenClick(YOUR_ACCOUNT);
    System.out.println("HOME_PAGE: Navigating to the [SIGNIN_PAGE]...\n");
  }

  public String getNumberOfItemsListedInShoppingCartIcon() {
    return getElementText(SHOPPING_CART_COUNT);
  }

  public void selectShoppingCartIcon() {
    click(SHOPPING_CART_ICON);
  }

}
