package manager;

import manager.hbm.ContactRecord;
import manager.hbm.GroupRecord;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import java.util.List;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {
  private SessionFactory sessionFactory;
  public HibernateHelper(ApplicationManager manager) {
    super(manager);
      sessionFactory = new Configuration()
              .addAnnotatedClass(GroupRecord.class)
              .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
              .setProperty(AvailableSettings.USER, "root")
              .setProperty(AvailableSettings.PASS, "")
              .buildSessionFactory();
  }


  /*static List<GroupData> converGrouptList(List<GroupRecord> records) {
    List<GroupData> result = new ArrayList<>();
    for (var record: records) {
      result.add(convert(record));
    }
    return result;
  } */

  static List<GroupData> converGrouptList(List<GroupRecord> records) {
    return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
  }
  private static GroupData convert(GroupRecord record) {
    return new GroupData("" + record.id, record.name, record.header, record.footer, "0",null);
  }

  private static GroupRecord convert(GroupData data) {
    var id = data.id();
    if ("".equals(id)) {
      id = "0";
    }
    return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
  }

 /* static List<ContactData> convertContactList(List<ContactRecord> records) {
    List<ContactData> result = new ArrayList<>();
    for (var record : records) {
      result.add(convert(record));
    }
    return result;
  } */
  static List<ContactData> convertContactList(List<ContactRecord> records) {
    return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
  }

  private static ContactData convert(ContactRecord record) {
    return new ContactData().withId("" + record.id)
            .withName(record.firstname)
            .withLastName(record.lastname)
            .withMobilePhone(record.mobile);
  }

  private static ContactRecord convert(ContactData data) {
    var id = data.id();
    if ("".equals(id)) {
      id = "0";
    }
    return new ContactRecord(Integer.parseInt(id), data.firstName(), data.lastName(), data.mobilePhone());
  }


  public List<GroupData> getGroupList() {
    return converGrouptList(sessionFactory.fromSession(session -> {
      return session.createQuery("from GroupRecord", GroupRecord.class).list();
    }));
  }

  public long getGroupCount() {
    return sessionFactory.fromSession(session -> {
      return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
    });
  }

  public void createGroup(GroupData groupData) {
    sessionFactory.inSession(session ->{
      session.getTransaction().begin();
      session.persist(convert(groupData));
      session.getTransaction().commit();
    });
  }

  public List<ContactData> getContactsInGroup(GroupData group) {
    return sessionFactory.fromSession(session -> {
    return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
    });
  }
}
