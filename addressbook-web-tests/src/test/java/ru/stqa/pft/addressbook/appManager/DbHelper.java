package ru.stqa.pft.addressbook.appManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.models.ContactData;
import ru.stqa.pft.addressbook.models.Contacts;
import ru.stqa.pft.addressbook.models.GroupData;
import ru.stqa.pft.addressbook.models.Groups;

import java.sql.*;
import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {

    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData").list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }
  public Contacts contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }
  public ContactData contactWithoutGroup() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where groups.size = 0 and deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return result.iterator().next();
  }
  public ContactData contactById(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery(String.format("from ContactData where id = %s ", id)).list();
    session.getTransaction().commit();
    session.close();
    return result.iterator().next();
  }
  public Contacts verifyContactNotInGroup() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select addressbook.id, firstname, lastname, address, home, mobile" +
              ", work, phone2, email, email2, email3 from addressbook " +
              "join address_in_groups ON addressbook.id=address_in_groups.id where (select count(*) from addressbook) = (select count(*) from address_in_groups)");
      Contacts contacts = new Contacts();
      while (rs.next()) {
        contacts.add(new ContactData().withId(rs.getInt("id")).withFirstname(rs.getString("firstname"))
                .withLastname(rs.getString("lastname")).withAddress(rs.getString("address"))
                .withHomePhoneNumber(rs.getString("home")).withMobPhoneNumber(rs.getString("mobile"))
                .withWorkPhoneNumber(rs.getString("work")).withEmail(rs.getString("email"))
                .withEmail2(rs.getString("email2")).withEmail3(rs.getString("email3")));
      }
      rs.close();
      st.close();
      conn.close();

      //System.out.println(contacts);
      return new Contacts(contacts);

      // Do something with the Connection

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    } return new Contacts();
  }
  public Contacts verifyContactInGroup() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select addressbook.id, firstname, lastname, address, home, mobile" +
              ", work, phone2, email, email2, email3 from addressbook " +
              "join address_in_groups ON addressbook.id=address_in_groups.id");
      Contacts contacts = new Contacts();
      while (rs.next()) {
        contacts.add(new ContactData().withId(rs.getInt("id")).withFirstname(rs.getString("firstname"))
                .withLastname(rs.getString("lastname")).withAddress(rs.getString("address"))
                .withHomePhoneNumber(rs.getString("home")).withMobPhoneNumber(rs.getString("mobile"))
                .withWorkPhoneNumber(rs.getString("work")).withEmail(rs.getString("email")).withEmail2(rs.getString("email2"))
                .withEmail3(rs.getString("email3")));
      }
      rs.close();
      st.close();
      conn.close();

      //System.out.println(contacts);
      return new Contacts(contacts);

      // Do something with the Connection

    } catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    } return new Contacts();
  }
  public ContactData contactWithGroup() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where groups.size > 0 and deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return result.iterator().next();
  }
  public ContactData getContactById(int contactId) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    ContactData contact = (ContactData) session.createQuery("from ContactData where id = :id and deprecated = '0000-00-00'")
            .setParameter("id", contactId)
            .uniqueResult();
    session.getTransaction().commit();
    session.close();
    return contact;
  }
}