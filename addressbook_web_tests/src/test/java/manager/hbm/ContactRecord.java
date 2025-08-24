package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

  public ContactRecord() {
  }


  @Id
  @Column(name = "id")
  public int id;
  @Column(name = "firstname")
  public String firstname;
  @Column(name = "lastname")
  public String lastname;
  @Column(name = "mobile")
  public String mobile;

  @Column(name = "work")
  public String work;

  public Date deprecated = new Date();

  public ContactRecord(int id, String firstname, String lastname, String mobile, String work) {

    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.mobile = mobile;
    this.work = work;
  }
}
