package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


  public static List<ContactData> contactProvider() throws IOException {
    var result = new ArrayList<ContactData>();
    for (var first_name : List.of("", "First name")) {
      for (var last_name : List.of("", "Last name")) {
        for (var mobile_phone : List.of("", "+7(555)4443322")) {
          for (var photo : List.of("src/test/resources/images/avatar.png"))
          result.add(new ContactData().withName(first_name).withLastName(last_name).withMobilePhone(mobile_phone).withPhoto(photo));
        }
      }
    }
    ObjectMapper mapper = new ObjectMapper();
    var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {});
    result.addAll(value);
    return result;
  }

  public static List<ContactData> negativeContactProvider() {
    var result = new ArrayList<ContactData>(List.of(
            new ContactData("", "contact name'", "", "", "", "0", "")));
    return result;
  }

  /*public static List<ContactData> singleRandomContactProvider() throws IOException {
    return List.of(new ContactData()
            .withName(CommonFunctions.randomString(10))
            .withLastName(CommonFunctions.randomString(10))
            .withMobilePhone(CommonFunctions.randomString(10))
            .withPhoto(""));
    if (app.jdbc().getGroupList().isEmpty()) {
      app.groups().createGroup(new GroupData("", "group name", "group header", "group footer", "", ""));
    }
  } */

  @ParameterizedTest
  @MethodSource("contactProvider")
  public void canCreateMultipleContactTest(ContactData contact) {
    var oldContacts = app.contacts().getList();
    app.contacts().createContact(contact);
    var newContacts = app.contacts().getList();
    Comparator<ContactData> compareById = (o1, o2) -> {
      return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    };
    newContacts.sort(compareById);
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id())
            .withName(newContacts.get(newContacts.size() - 1).firstName())
            .withLastName(newContacts.get(newContacts.size() - 1).lastName())
            .withMobilePhone(newContacts.get(newContacts.size() - 1).mobilePhone())
            .withPhoto(newContacts.get(newContacts.size() - 1).photo())
            .withDomainId(newContacts.get(newContacts.size() - 1).domainId())
            .withNickname(newContacts.get(newContacts.size() - 1).nickname()));
    expectedList.sort(compareById);
    Assertions.assertEquals(newContacts, expectedList);
  }

  @ParameterizedTest
  @MethodSource("negativeContactProvider")
  public void canNotCreateMultipleContactTest(ContactData contact) {
    var oldContacts = app.contacts().getList();
    app.contacts().createContact(contact);
    var newContacts = app.contacts().getList();
    Assertions.assertEquals(newContacts, oldContacts);
  }

  @ParameterizedTest
  @MethodSource("singleRandomContactProvider")
  public void canCreateContactDbAssertTest(ContactData contact) {
    var oldContacts = app.jdbc().getContactList();
    app.contacts().createContact(contact);
    var newContacts = app.jdbc().getContactList();
    Comparator<ContactData> compareById = (o1, o2) -> {
      return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
    };
    newContacts.sort(compareById);
    var maxId = newContacts.get(newContacts.size() - 1).id();
    var expectedList = new ArrayList<>(oldContacts);
    expectedList.add(contact.withId(maxId));
    expectedList.sort(compareById);
    Assertions.assertEquals(newContacts, expectedList);
  }

  @ParameterizedTest
  @MethodSource("negativeContactProvider")
  public void canNotCreateMultipleContactDbAssertTest(ContactData contact) {
    var oldContacts = app.jdbc().getContactList();
    app.contacts().createContact(contact);
    var newContacts = app.jdbc().getContactList();
    Assertions.assertEquals(newContacts, oldContacts);
  }

 @Test
  public void canCreateContactInGroupDbAssertTest() {
   var contact = new ContactData()
           .withName(CommonFunctions.randomString(10))
           .withLastName(CommonFunctions.randomString(10))
           .withMobilePhone(CommonFunctions.randomString(10))
           .withPhoto("");
  if (app.hbm().getGroupList().isEmpty()) {
     app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer", "", ""));
   }
   var group = app.hbm().getGroupList().get(0);
   var oldRelated = app.hbm().getContactsInGroup(group);
   app.contacts().createContact(contact, group);
   var newRelated = app.hbm().getContactsInGroup(group);
   Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
  }
}
