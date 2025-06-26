package model;

public record ContactData(String firstName, String lastName, String mobilePhone) {
  public ContactData() {
    this("", "", "");
  }

  public ContactData withName(String firstName, String mobilePhone) {
    return new ContactData(firstName, this.lastName, mobilePhone);
  }

  public ContactData withLastName(String lastName, String mobilePhone) {
    return new ContactData(this.firstName, lastName, mobilePhone);
  }
}
