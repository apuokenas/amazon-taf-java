package pages;

import enums.Products;
import enums.Url;
import org.openqa.selenium.By;
import org.testng.TestException;
import utils.CommonUtils;

/**
 * Created by Mantas Tumėnas on 2017-04-21.
 */
public class ProductPage extends CommonUtils {

  private final By PRODUCT_TITLE = By.cssSelector("#productTitle");
  private final By AUTHOR = By.cssSelector(".a-link-normal.contributorNameID");
  private final By EDITION = By.cssSelector(".a-size-medium.a-color-secondary.a-text-normal");
  private final By PRICE = By.cssSelector(".a-size-medium.a-color-price.offer-price.a-text-normal");
  private final By ADD_TO_CART = By.cssSelector("#add-to-cart-button");

  public void navigateToProductPage(Products product) {
    String url = Url.BASE_URL.getURL() + Url.PRODUCT_SECTION.getURL() + "/" + product.getProductId();
    System.out.println("PRODUCT_PAGE: Navigating to " + url + "...");
    navigateToURL(url);
  }

  public void verifyProductTitle(String expectedTitle) {
    String actualTitle = getProductTitle();
    System.out.println("PRODUCT_PAGE: Verifying that the product title is '" + expectedTitle + "'...");
    if (!actualTitle.equals(expectedTitle)) {
      throw new TestException("ERROR in PRODUCT_PAGE: The actual product title is '" +
        actualTitle + "', while expected '" + expectedTitle + "'!");
    }
  }

  public void clickAddToCart() {
    System.out.println("PRODUCT_PAGE: Clicking on [ADD_TO_CART] button.\n");
    click(ADD_TO_CART);
  }

  public String getProductTitle() {
    return getElementText(PRODUCT_TITLE);
  }

  public String getAuthor() {
    return getElementText(AUTHOR);
  }

  public String getEdition() {
    return getElementText(EDITION);
  }

  public String getPrice() {
    return getElementText(PRICE);
  }

}
