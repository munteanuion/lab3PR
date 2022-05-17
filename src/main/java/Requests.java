import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.jsoup.Connection.Method.*;
import static org.jsoup.Jsoup.connect;

public class Requests
{
  private static Map<String, String> cookies = Authentification.getCookies();

  public static String head() throws IOException
  {
    Response response = connect("https://pdalife.to/android/igry/").method(HEAD).cookies(cookies).execute();
    return response.contentType();
  }

  public static Map<String, List<String>> optionsResponse() throws IOException
  {
    Response response = connect("https://pdalife.to/android/igry/").method(OPTIONS).cookies(cookies).execute();
    return response.multiHeaders();
  }

  public static String searchName(String link) throws Exception
  {
    Response response = connect(link).method(GET).cookies(cookies).execute();
    String text = response.body();
    Pattern pattern = Pattern.compile("ion654");
    Matcher matcher = pattern.matcher(text);
    if (matcher.find())
    {
      return "Account name is: " + matcher.group();
    }
    throw new Exception("Account name not found on this page.");
  }

  public static void getLinks() throws Exception
  {
    Document document;
    document = Jsoup.connect("https://pdalife.to/android/igry/").get();
    Elements elements = document.select("a[href]");
    Set<String> links = new HashSet<String>();
    for (Element element : elements)
    {
      links.add(element.attr("abs:href"));
    }
    System.out.println("\n" + links);
  }

  public static void searchEmails() throws Exception
  {
    Response response = connect("https://pdalife.to/my/settings/").method(GET).cookies(cookies).execute();
    String text = response.body();
    Pattern pattern = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
    Matcher matcher = pattern.matcher(text);
    Set<String> email = new HashSet<String>();
    while (matcher.find())
    {
      email.add(matcher.group());
    }
    System.out.println(email);
  }
}
