package model;

public record GroupData(String id, String name, String header, String footer, String domainId, String groupParentId) {

  public GroupData() {
    this("", "", "", "", "0", null);
  }

  public GroupData withId(String id) {
    return new GroupData(id, this.name, this.header, this.footer, this.domainId, this.groupParentId);
  }
  public GroupData withName(String name) {
    return new GroupData(this.id, name, this.header, this.footer, this.domainId, this.groupParentId);
  }

  public GroupData withHeader(String header) {
    return new GroupData(this.id, this.name, header, this.footer, this.domainId, this.groupParentId);
  }

  public GroupData withFooter(String footer) {
    return new GroupData(this.id, this.name, this.header, footer, this.domainId, this.groupParentId);
  }

  public GroupData withDomainId(String domainId) {
    return new GroupData(this.id, this.name, this.header, this.footer, domainId, this.groupParentId);
  }

  public GroupData withGroupParentId(String groupParentId) {
    return new GroupData(this.id, this.name, this.header, this.footer, this.domainId, groupParentId);
  }
}
