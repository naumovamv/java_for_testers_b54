package model;

public record ContactData(String id, String firstName, String lastName, String mobilePhone, String photo) {
  public ContactData() {
    this("", "", "", "", "");
  }

  public ContactData withId(String id) {
    return new ContactData(id, this.firstName, this.lastName, this.mobilePhone, this.photo);
  }

  public ContactData withName(String firstName) {
    return new ContactData(this.id, firstName, this.lastName, this.mobilePhone, this.photo);
  }

  public ContactData withLastName(String lastName) {
    return new ContactData(this.id, this.firstName, lastName, this.mobilePhone, this.photo);
  }

  public ContactData withMobilePhone(String mobilePhone) {
    return new ContactData(this.id, this.firstName, this.lastName, mobilePhone, this.photo);
  }

  public ContactData withPhoto(String photo) {
    return new ContactData(this.id, this.firstName, this.lastName, this.mobilePhone, photo);
  }
}
