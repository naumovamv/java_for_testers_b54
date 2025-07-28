package manager;

import manager.hbm.GroupRecord;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import java.util.List;

import java.util.ArrayList;

public class HibernateHelper extends HelperBase {
  private SessionFactory sessionFactory;
  public HibernateHelper(ApplicationManager manager) {
    super(manager);
    SessionFactory sessionFactory = new Configuration()
            //.addAnnotatedClass(Book.class)
            .addAnnotatedClass(GroupRecord.class)
            .setProperty(AvailableSettings.JAKARTA_JDBC_URL, "jdbc:mysql://localhost/addressbook")
            .setProperty(AvailableSettings.JAKARTA_JDBC_USER, "root")
            .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, "")
            .setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQLDialect")
            .buildSessionFactory();
  }


  static List<GroupData> convertList(List<GroupRecord> records) {
    List<GroupData> result = new ArrayList<>();
    for (var record: records) {
      result.add(convert(record));
    }
    return result;
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
  public List<GroupData> getGroupList() {
    return convertList(sessionFactory.fromSession(session -> {
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
}
