public class TestRequests
{
  public static void main(String[] args) throws Exception
  {
    System.out.println("Content-Type: " + Requests.head());
    System.out.println("Options response: " + Requests.optionsResponse());
    System.out.println("\n" + Requests.searchName("https://pdalife.to/my/profile/"));
    System.out.println("The list of all game links: ");
    Requests.getLinks();
    System.out.println("\nSearch emails from page:");
    Requests.searchEmails();
  }

}
