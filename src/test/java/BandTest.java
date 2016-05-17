import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;

public class BandTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Band_instantiatesCorrectly_true() {
    Band newBand = new Band("Radiohead", "Alternative");
    assertEquals(true, newBand instanceof Band);
  }

  @Test
  public void getName_returnsBandName_String() {
    Band newBand = new Band("Radiohead", "Alternative");
    assertEquals("Radiohead", newBand.getName());
  }

  @Test
  public void getGenre_returnsBandGenre_String() {
    Band newBand = new Band("Radiohead", "Alternative");
    assertEquals("Alternative", newBand.getGenre());
  }

  @Test
  public void save_savesBand_true() {
    Band newBand = new Band("Radiohead", "Alternative");
    newBand.save();
    assertTrue(newBand.all().size() == 1);
  }

  @Test
  public void all_returnsListOfBands_List() {
    Band newBand = new Band("Radiohead", "Alternative");
    newBand.save();
    assertTrue(newBand.all() instanceof List);
  }

  @Test
  public void find_returnsBand_true() {
    Band newBand = new Band("Radiohead", "Alternative");
    newBand.save();
    Band foundBand = Band.find(newBand.getId());
    assertEquals(newBand.getName(), foundBand.getName());
  }

  @Test
  public void addVenue_addsVenueToBand_true() {
    Band newBand = new Band("Radiohead", "Alternative");
    newBand.save();
    Venue newVenue = new Venue("The Fillmore", "415-346-3000", "San Francisco", "CA");
    newVenue.save();
    newBand.addVenue(newVenue);
    assertTrue(newBand.getVenues().size() > 0);
  }

  @Test
  public void delete_deletesBand_true() {
    Band newBand = new Band("Radiohead", "Alternative");
    newBand.save();
    newBand.delete();
    assertTrue(newBand.getVenues().size() == 0);
  }

  @Test
  public void delete_deletesBandFromJoin_true() {
    Band newBand = new Band("Radiohead", "Alternative");
    newBand.save();
    Venue newVenue = new Venue("The Fillmore", "415-346-3000", "San Francisco", "CA");
    newVenue.save();
    newBand.addVenue(newVenue);
    newBand.delete();
    assertTrue(newVenue.getBands().size() == 0);
  }
}
