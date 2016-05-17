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

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Band)) {
      return false;
    } else {
      Band newBand = (Band) obj;
      return this.getId() == newBand.getId() &&
      this.getName() == newBand.getName() &&
      this.getGenre() == newBand.getGenre();
    }
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

  public static Band find(int idInput) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM bands WHERE id=:id";
      return con.createQuery(sql)
        .addParameter("id", idInput)
        .executeAndFetchFirst(Band.class);
    }
  }

  public void addVenue(Venue venue) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands_venues(band_id, venue_id) VALUES (:band_id, :venue_id)";
      con.createQuery(sql)
        .addParameter("band_id", this.id)
        .addParameter("venue_id", venue.getId())
        .executeUpdate();
    }
  }

  public List<Venue> getVenues() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT venue_id FROM bands_venues WHERE band_id=:id";
      List<Integer> venueIds = con.createQuery(sql)
        .addParameter("id", this.getId())
        .executeAndFetch(Integer.class);

      List<Venue> venues = new ArrayList<Venue>();

      for (Integer venueId : venueIds) {
        String venueQuery = "SELECT * FROM venues WHERE id=:venueId";
        Venue venue = con.createQuery(venueQuery)
          .addParameter("venueId", venueId)
          .executeAndFetchFirst(Venue.class);
        venues.add(venue);
      }
      return venues;
    }
  }

}
