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

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Venue)) {
      return false;
    } else {
      Venue newVenue = (Venue) obj;
      return this.getId() == newVenue.getId() &&
      this.getName() == newVenue.getName() &&
      this.getNumber() == newVenue.getNumber() &&
      this.getCity() == newVenue.getCity() &&
      this.getState() == newVenue.getState();
    }
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

  public void addBand(Band band) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands_venues(band_id, venue_id) VALUES (:band_id, :venue_id)";
      con.createQuery(sql)
        .addParameter("venue_id", this.id)
        .addParameter("band_id", band.getId())
        .executeUpdate();
    }
  }

  public List<Band> getBands() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT band_id FROM bands_venues WHERE venue_id=:id";
      List<Integer> bandIds = con.createQuery(sql)
        .addParameter("id", this.getId())
        .executeAndFetch(Integer.class);

      List<Band> bands = new ArrayList<Band>();

      for (Integer bandId : bandIds) {
        String bandQuery = "SELECT * FROM bands WHERE id=:bandId";
        Band band = con.createQuery(bandQuery)
          .addParameter("bandId", bandId)
          .executeAndFetchFirst(Band.class);
        bands.add(band);
      }
      return bands;
    }
  }
}
