import java.util.*;
import org.sql2o.*;

public class Band {
  private int id;
  private String name;
  private String genre;

  public Band(String name, String genre) {
    this.name = name;
    this.genre = genre;
  }

  public String getName() {
    return name;
  }

  public String getGenre() {
    return genre;
  }

  public static List<Band> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM bands";
      List<Band> all = con.createQuery(sql)
        .executeAndFetch(Band.class);
      return all;
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands(name, genre) VALUES (:name, :genre)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("genre", this.genre)
        .executeUpdate()
        .getKey();
    }
  }
}
