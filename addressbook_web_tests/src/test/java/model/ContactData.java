package model;

public record ContactData(String id, String firstName, String lastName, String mobilePhone) {
  public ContactData() {
    this("", "", "", "");
  }

  public ContactData withId(String id) {
    return new ContactData(id, this.firstName, this.lastName, this.mobilePhone );
  }

  public ContactData withName(String firstName) {
    return new ContactData(this.id, firstName, this.lastName, this.mobilePhone);
  }

  public ContactData withLastName(String lastName) {
    return new ContactData(this.id, this.firstName, lastName, this.mobilePhone);
  }

  public ContactData withMobilePhone(String mobilePhone) {
    return new ContactData(this.id, this.firstName, this.lastName, mobilePhone);
  }
}
