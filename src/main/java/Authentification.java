import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Map;


public class Authentification
{
  private static String LOGIN_INPUT = "ident";
  private static String PASSWORD_INPUT = "password";
  private static String LINK = "https://pdalife.to/login/";

  public static Map<String, String> getCookies()
  {
    Authenticator.setDefault(
        new Authenticator()
        {
          public PasswordAuthentication getPasswordAuthentication()
          {
            return new PasswordAuthentication(ConnexionData.LOGIN, ConnexionData.PASSWORD.toCharArray());
          }
        }
    );

    System.setProperty("http.proxyHost", "188.165.59.127");
    System.setProperty("http.proxyPort", "3128");

    Connection.Response response = null;

    try
    {
      response = Jsoup.connect(LINK)
          .referrer(LINK)
          .method(Connection.Method.POST)
          .data(LOGIN_INPUT, ConnexionData.LOGIN)
          .data(PASSWORD_INPUT, ConnexionData.PASSWORD)
          .execute();
    } catch (IOException e)
    {
      e.printStackTrace();
      throw new RuntimeException("Login error");
    }
    return response.cookies();
  }
}

