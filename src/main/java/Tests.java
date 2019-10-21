import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.JsonResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    @BeforeAll
    public static void setUp(){
        Configuration.browser = "firefox";
        System.setProperty("webdriver.gecko.driver", "src/driver/geckodriver.exe");
    }
    @Test
    public void apiCityTest(){
        String city = "Kazan";
        Unirest.config().reset();
        Unirest.config()
                .socketTimeout(500)
                .connectTimeout(5000)
                .setDefaultHeader("Accept", "application/json")
                .followRedirects(false)
                .enableCookieManagement(true)
                .enableCookieManagement(true);
        HttpResponse<String> response = Unirest.get("https://www.onetwotrip.com/_hotels/api/suggestRequest?query="+city)
                .header("User-Agent", "PostmanRuntime/7.18.0")
                .header("Accept", "*/*")
                .header("Cache-Control", "no-cache")
                .header("Postman-Token", "08733e6f-6eea-4a24-9220-72e722bda956,b856a737-794b-4644-84c5-64d99b1c9ecf")
                .header("Host", "www.onetwotrip.com")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Cookie", "hapi_id=AAAAAV2t+qydRCzIA3vAAg==; referrer_first=dir; referrer_hist=dir; referrer=; accept_language=ru; ENVID=production-a|Xa36w")
                .header("Connection", "keep-alive")
                .header("cache-control", "no-cache")
                .asString();
        if (response.getStatus()==200){
            assertEquals(true,response.getBody().contains(city));
        }
        else {
            System.out.println("Error: HTTP code is NOT 200");
            assertEquals(200, response.getStatus());
        }
        switch (response.getStatus()){
            case (200):


        }


    }

    @Test
    public void redirectionTest() {
        Unirest.config().reset();
        Unirest.config()
                .socketTimeout(500)
                .connectTimeout(5000)
                .setDefaultHeader("Accept", "application/json")
                .followRedirects(false)
                .enableCookieManagement(true)
                .enableCookieManagement(false);
        HttpResponse<String> response = Unirest.get("http://onetwotrip.com")
                .header("User-Agent", "PostmanRuntime/7.18.0")
                .header("Accept", "*/*")
                .header("Cache-Control", "no-cache")
                .header("Postman-Token", "7203649f-5372-4567-8617-952be58cc95a,4d26f2dd-2123-4c97-ad95-ca8ab1d347e6")
                .header("Host", "onetwotrip.com")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Connection", "keep-alive")
                .header("cache-control", "no-cache")
                .asString();
        System.out.println(response.getStatus());
        switch (response.getStatus()){
            case(301):
                assertEquals(301, response.getStatus());
                Unirest.config().reset();
            case(302):
                assertEquals(301, response.getStatus());
                Unirest.config().reset();
                }
}
    @Test
    public void urlCheck() {
        open("http://onetwotrip.com");
        assertEquals(true,WebDriverRunner.url().contains("https://www.onetwotrip.com"));
           }
}
