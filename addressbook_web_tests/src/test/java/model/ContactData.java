package model;

public record ContactData(String id, String firstName, String lastName, String mobilePhone, String photo, String domainId, String nickname) {
  public ContactData() {
    this("", "", "", "", "","0", "");
  }

  public ContactData withId(String id) {
    return new ContactData(id, this.firstName, this.lastName, this.mobilePhone, this.photo, this.domainId, this.nickname);
  }

  public ContactData withName(String firstName) {
    return new ContactData(this.id, firstName, this.lastName, this.mobilePhone, this.photo, this.domainId, this.nickname);
  }

  public ContactData withLastName(String lastName) {
    return new ContactData(this.id, this.firstName, lastName, this.mobilePhone, this.photo, this.domainId, this.nickname);
  }

  public ContactData withMobilePhone(String mobilePhone) {
    return new ContactData(this.id, this.firstName, this.lastName, mobilePhone, this.photo, this.domainId, this.nickname);
  }

  public ContactData withPhoto(String photo) {
    return new ContactData(this.id, this.firstName, this.lastName, this.mobilePhone, photo, this.domainId, this.nickname);
  }

  public ContactData withDomainId(String domainId) {
    return new ContactData(this.id, this.firstName, this.lastName, this.mobilePhone, this.photo, domainId, this.nickname);
  }

  public ContactData withNickname(String nickname) {
    return new ContactData(this.id, this.firstName, this.lastName, this.mobilePhone, this.photo, this.domainId, nickname);
  }
}
