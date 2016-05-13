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

}
