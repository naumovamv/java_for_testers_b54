package model;

public record ContactData(
        String id,
        String firstName,
        String lastName,
        String mobilePhone,
        String photo,
        String domainId,
        String nickname,
        String home,
        String work,
        String secondary) {
  public ContactData() {
    this("", "", "", "", "","0", "", "", "", "");
  }

  public ContactData withId(String id) {
    return new ContactData(id, this.firstName, this.lastName, this.mobilePhone, this.photo, this.domainId, this.nickname, this.home, this.work, this.secondary);
  }

  public ContactData withName(String firstName) {
    return new ContactData(this.id, firstName, this.lastName, this.mobilePhone, this.photo, this.domainId, this.nickname, this.home, this.work, this.secondary);
  }

  public ContactData withLastName(String lastName) {
    return new ContactData(this.id, this.firstName, lastName, this.mobilePhone, this.photo, this.domainId, this.nickname, this.home, this.work, this.secondary);
  }

  public ContactData withMobilePhone(String mobilePhone) {
    return new ContactData(this.id, this.firstName, this.lastName, mobilePhone, this.photo, this.domainId, this.nickname, this.home, this.work, this.secondary);
  }

  public ContactData withPhoto(String photo) {
    return new ContactData(this.id, this.firstName, this.lastName, this.mobilePhone, photo, this.domainId, this.nickname, this.home, this.work, this.secondary);
  }

  public ContactData withDomainId(String domainId) {
    return new ContactData(this.id, this.firstName, this.lastName, this.mobilePhone, this.photo, domainId, this.nickname, this.home, this.work, this.secondary);
  }

  public ContactData withNickname(String nickname) {
    return new ContactData(this.id, this.firstName, this.lastName, this.mobilePhone, this.photo, this.domainId, nickname, this.home, this.work, this.secondary);
  }

  public ContactData withHome(String home) {
    return new ContactData(this.id, this.firstName, this.lastName, this.mobilePhone, this.photo, this.domainId, this.nickname, home, this.work, this.secondary);
  }

  public ContactData withWork(String work) {
    return new ContactData(this.id, this.firstName, this.lastName, this.mobilePhone, this.photo, this.domainId, this.nickname, this.home, work, this.secondary);
  }

  public ContactData withSecondary(String secondary) {
    return new ContactData(this.id, this.firstName, this.lastName, this.mobilePhone, this.photo, this.domainId, this.nickname, this.home, this.work, secondary);
  }
}
