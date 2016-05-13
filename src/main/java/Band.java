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
}
