package enums;

/**
 * Created by Mantas Tumėnas on 2017-04-21.
 */
public enum Url {

  BASE_URL("http://www.amazon.fr"),
  PRODUCT_SECTION("/gp/product"),
  SIGNOUT("/gp/flex/sign-out.html");

  String url;

  Url(String url) {
    this.url = url;
  }

  public String getURL() {
    return url;
  }

}
