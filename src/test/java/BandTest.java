import org.junit.*;
import java.util.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class BandTest {
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
}
