import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Band Tracker!");
  }

  @Test
  public void addsBand() {
    Band newBand = new Band("Riff Raff", "Hip-Hop");
    newBand.save();
    String path = String.format("http://localhost:4567/band/%d", newBand.getId());
    goTo(path);
    assertThat(pageSource()).contains("Riff Raff");
  }

  @Test
  public void addsVenue() {
    Venue newVenue = new Venue("Club 33", "714-688-9000", "Anaheim", "California");
    newVenue.save();
    String path = String.format("http://localhost:4567/venue/%d", newVenue.getId());
    goTo(path);
    assertThat(pageSource()).contains("Club 33");
  }

  @Test
  public void deletesBand() {
    Band newBand = new Band("Riff Raff", "Hip-Hop");
    newBand.save();
    newBand.delete();
    String path = String.format("http://localhost:4567/");
    goTo(path);
    assertThat(pageSource()).doesNotContain("Riff Raff");
  }

  @Test
  public void addsVenueToBand() {
    Band newBand = new Band("Riff Raff", "Hip-Hop");
    newBand.save();
    Venue newVenue = new Venue("Club 33", "714-688-9000", "Anaheim", "California");
    newVenue.save();
    newBand.addVenue(newVenue);
    String path = String.format("http://localhost:4567/band/%d", newBand.getId());
    goTo(path);
    assertThat(pageSource()).contains("Club 33");
  }

  @Test
  public void addsBandToVenue() {
    Band newBand = new Band("Riff Raff", "Hip-Hop");
    newBand.save();
    Venue newVenue = new Venue("Club 33", "714-688-9000", "Anaheim", "California");
    newVenue.save();
    newVenue.addBand(newBand);
    String path = String.format("http://localhost:4567/venue/%d", newVenue.getId());
    goTo(path);
    assertThat(pageSource()).contains("Riff Raff");
  }

  @Test
  public void updatesBand() {
    Band newBand = new Band("Riff Raff", "Hip-Hop");
    newBand.save();
    newBand.updateBand("Radiohead", "Alternative");
    String path = String.format("http://localhost:4567/band/%d", newBand.getId());
    goTo(path);
    assertThat(pageSource()).contains("Radiohead");
  }

}
