import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GroupCreationTests {
  private static WebDriver driver;

  @BeforeEach
  public void setUp() {
    if (driver == null) {
      driver = new FirefoxDriver();
      Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
      driver.get("http://localhost/addressbook/");
      driver.findElement(By.name("user")).click();
      driver.findElement(By.name("user")).sendKeys("admin");
      driver.findElement(By.name("pass")).sendKeys("secret");
      driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
    }
  }

  private boolean isElementPresent(By locator) {
    try {
      driver.findElement(locator);
      return true;
    } catch (NoSuchElementException exception) {
      return false;
    }
  }

  @Test
  public void canCreateGroupTest() {
    if (!isElementPresent(By.name("new"))) {
      driver.findElement(By.linkText("groups")).click();
    }

    driver.findElement(By.name("new")).click();
    driver.findElement(By.name("group_name")).click();
    driver.findElement(By.name("group_name")).sendKeys("Group name");
    driver.findElement(By.name("group_header")).click();
    driver.findElement(By.name("group_header")).sendKeys("Group header");
    driver.findElement(By.name("group_footer")).click();
    driver.findElement(By.name("group_footer")).sendKeys("Group footer");
    driver.findElement(By.name("submit")).click();
    driver.findElement(By.linkText("groups")).click();
  }

  @Test
  public void canDeleteGroupTest() {
    if (!isElementPresent(By.name("new"))) {
      driver.findElement(By.linkText("groups")).click();
    }
    if (!isElementPresent(By.name("selected[]"))) {
      canCreateGroupTest();
    }
    driver.findElement(By.name("selected[]")).click();
    driver.findElement(By.name("delete")).click();
    driver.findElement(By.linkText("group page")).click();
  }
}
