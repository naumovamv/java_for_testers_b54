package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase {

  public ContactHelper(ApplicationManager manager) {
    super(manager);
  }

  public void createContact(ContactData contact) {
    openContactPage();
    initContactCreation();
    fillContactForm(contact);
    submitContactCreation();
    returnToHomePage();
  }

  public void removeContact() {
    openContactPage();
    selectContact();
    removeSelectedContact();
    returnToHomePage();
  }

  private void removeSelectedContact() {
    click((By.xpath("//input[@value=\'Delete\']")));
  }

  public void openContactPage() {
    if (!manager.isElementPresent(By.name("searchstring"))) {
      click(By.name("add new"));
    }
  }

  public void isContactPresent(ApplicationManager applicationManager) {
    openContactPage();
    if (!manager.isElementPresent(By.name("selected[]"))) {
      manager.contacts().createContact(new ContactData());
    }
  }

  private void submitContactCreation() {
    click(By.name("submit"));
  }

  private void initContactCreation() {
    click(By.linkText("add new"));
  }

  private void returnToHomePage() {
    click(By.linkText("home"));
  }

    private void fillContactForm(ContactData contact) {
    type(By.name("firstname"), contact.firstName());
    type(By.name("lastname"), contact.lastName());
    type(By.name("mobile"), contact.mobilePhone());
  }

  private void selectContact() {
    click(By.name("selected[]"));
  }
}
