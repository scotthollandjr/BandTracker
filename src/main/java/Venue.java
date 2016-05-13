import java.util.*;
import org.sql2o.*;

public class Venue {
  private int id;
  private String name;
  private String number;
  private String city;
  private String state;

  public Venue(String name, String number, String city, String state) {
    this.name = name;
    this.number = number;
    this.city = city;
    this.state = state;
  }

  public String getName() {
    return name;
  }

  public String getNumber() {
    return number;
  }

  public String getCity() {
    return city;
  }

  public String getState() {
    return state;
  }

  public int getId() {
    return id;
  }

  public static List<Venue> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM venues";
      List<Venue> all = con.createQuery(sql)
        .executeAndFetch(Venue.class);
      return all;
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO venues(name, number, city, state) VALUES (:name, :number, :city, :state)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("number", this.number)
        .addParameter("city", this.city)
        .addParameter("state", this.state)
        .executeUpdate()
        .getKey();
    }
  }

  public static Venue find(int idInput) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM venues WHERE id=:id";
      return con.createQuery(sql)
        .addParameter("id", idInput)
        .executeAndFetchFirst(Venue.class);
    }
  }
}
