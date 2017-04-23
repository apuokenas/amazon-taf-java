package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Mantas Tumėnas on 2017-04-21.
 */
public class LoadProperties {

  public static Properties user = loadProperties("src/test/java/properties/user.properties");

  private static Properties loadProperties(String filePath) {
    Properties properties = new Properties();
    try {
      FileInputStream f = new FileInputStream(filePath);
      properties.load(f);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return properties;
  }

}
