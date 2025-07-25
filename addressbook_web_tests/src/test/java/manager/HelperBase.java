package manager;

import org.openqa.selenium.By;

import java.nio.file.Path;
import java.nio.file.Paths;

public class HelperBase {
  protected final ApplicationManager manager;

  public HelperBase(ApplicationManager manager) {
    this.manager = manager;
  }

  protected void type(By locator, String text) {
    click(locator);
    manager.driver.findElement(locator).clear();
    manager.driver.findElement(locator).sendKeys(text);
  }

  protected void attach(By locator, String file) {
    Path path = Paths.get(file).toAbsolutePath();
    manager.driver.findElement(locator).sendKeys(path.toString());
  }


  protected void click(By locator) {
    manager.driver.findElement(locator).click();
  }
}
