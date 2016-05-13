import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class VenueTest {

  @Test
  public void Venue_instantiatesCorrectly_true() {
    Venue newVenue = new Venue("The Fillmore", "415-346-3000", "San Francisco", "CA");
    assertEquals(true, newVenue instanceof Venue);
  }

  @Test
  public void getName_returnsVenueName_String() {
    Venue newVenue = new Venue("The Fillmore", "415-346-3000", "San Francisco", "CA");
    assertEquals("The Fillmore", newVenue.getName());
  }

  @Test
  public void getNumber_returnsVenueNumber_String() {
    Venue newVenue = new Venue("The Fillmore", "415-346-3000", "San Francisco", "CA");
    assertEquals("415-346-3000", newVenue.getNumber());
  }

  @Test
  public void getCity_returnsVenueCity_String() {
    Venue newVenue = new Venue("The Fillmore", "415-346-3000", "San Francisco", "CA");
    assertEquals("San Francisco", newVenue.getCity());
  }

  @Test
  public void getState_returnsVenueState_String() {
    Venue newVenue = new Venue("The Fillmore", "415-346-3000", "San Francisco", "CA");
    assertEquals("CA", newVenue.getState());
  }
}
