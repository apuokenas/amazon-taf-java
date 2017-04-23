package pojo;

import org.testng.TestException;
import pages.ProductPage;

/**
 * Created by Mantas Tumėnas on 2017-04-21.
 */
public class Book {

  private String productTitle = "";
  private String author = "";
  private String offerPrice = "";
  private String edition = "";

  public Book() {
  }

  public void loadInfoFromProductPage() {
    ProductPage productPage = new ProductPage();
    String currentURL = productPage.getCurrentURL();
    if (!currentURL.contains("product")) {
      throw new TestException("LOAD_INFO ERROR: Product data should only be loaded from the [PRODUCT_PAGE]!\n" +
        "Current URL: " + currentURL + ".");
    } else {
      System.out.println("LOAD_INFO: Loading data from the Amazon...");
      this.productTitle = productPage.getProductTitle();
      this.author = productPage.getAuthor();
      this.edition = productPage.getEdition();
      this.offerPrice = productPage.getPrice();
    }
  }

  @Override
  public String toString() {
    return String.format(
      "\t* Product Title: " + this.productTitle + "\n"
        + "\t* Author: " + this.author + "\n"
        + "\t* Edition: " + this.edition + "\n"
        + "\t* Offer Price: " + this.offerPrice + "\n"
    );
  }

  public String getOfferPrice() {
    return offerPrice;
  }

}
