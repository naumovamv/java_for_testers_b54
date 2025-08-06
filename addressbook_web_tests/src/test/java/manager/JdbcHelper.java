package manager;

import model.ContactData;
import model.GroupData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase {

  public JdbcHelper(ApplicationManager manager) { super(manager); }

  public List<GroupData> getGroupList() {
    var groups = new ArrayList<GroupData>();
    try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
         var statement = conn.createStatement();
         var result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer, domain_id, group_parent_id FROM group_list"))
    {
          while (result.next()) {
            groups.add(new GroupData()
                    .withId(result.getString("group_id"))
                    .withName(result.getString("group_name"))
                    .withHeader(result.getString("group_header"))
                    .withFooter(result.getString("group_footer"))
                    .withDomainId(result.getString("domain_id"))
                    .withGroupParentId(result.getString("group_parent_id")));
          }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return groups;
  }

  public List<ContactData> getContactList() {
    var contacts = new ArrayList<ContactData>();
    try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
         var statement = conn.createStatement();
         var result = statement.executeQuery("SELECT id, firstname, lastname, mobile FROM addressbook"))
    {
      while (result.next()) {
        contacts.add(new ContactData()
                .withId(result.getString("id"))
                .withName(result.getString("firstname"))
                .withLastName(result.getString("lastname"))
                .withMobilePhone(result.getString("mobile")));
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return contacts;
  }


  public void checkConsistency() {
    try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
         var statement = conn.createStatement();
         var result = statement.executeQuery(
                 "SELECT * FROM 'address_in_groups' ag LEFT JOIN addressbook ab ON ab.id = ag.id WHERE ab.id IS NULL"))
    {
      if (result.next()) {
        throw new IllegalArgumentException("b is corrupted");
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
