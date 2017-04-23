package enums;

/**
 * Created by Mantas Tumėnas on 2017-04-21.
 */
public enum Products {

  ISTQB_FOUNDATION("1408044056", "Foundations of Software Testing: Istqb Certification");

  // The price will always fluctuate, while the product ID and the product title will be, suppose, constant.
  private String id;
  private String productTitle;

  Products(String id, String productTitle) {
    this.id = id;
    this.productTitle = productTitle;
  }

  public String getProductId() {
    return id;
  }

  public String getProductTitle() {
    return productTitle;
  }

}
