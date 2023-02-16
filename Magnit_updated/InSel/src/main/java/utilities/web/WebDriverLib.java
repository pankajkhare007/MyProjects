package utilities.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.remote.codec.w3c.W3CHttpCommandCodec;
import org.openqa.selenium.remote.codec.w3c.W3CHttpResponseCodec;
import utilities.reports.Reports;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Collections;

public class WebDriverLib {

    public static WebDriver driver ;
    static HttpCommandExecutor executor;
    static URL url;
    static SessionId session_id;
    static RemoteWebDriver driver2;
    public static String existingUrl;

    public static void LaunchBrowser(String browser, String uRL,String newSession) {


        switch(browser.toUpperCase())
        {
            case "CH":

                try {


                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }


                if(newSession.equalsIgnoreCase("true"))
                {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    executor = (HttpCommandExecutor) ((RemoteWebDriver) driver).getCommandExecutor();
                    url = executor.getAddressOfRemoteServer();
                    session_id = ((RemoteWebDriver) driver).getSessionId();
                    //SetGetSessionURL.setURLText(url+"");
                    //SetGetSessionURL.setSessionIDText(session_id+"");
                }
                else
                {
//                    existingUrl=SetGetSessionURL.getURLText();
//                    String existingSessionID=SetGetSessionURL.getSessionText();
//                    RemoteWebDriver driver2 = createDriverFromSession(new SessionId(existingSessionID), new URL(existingUrl));
//
//                    /// driver2 = createDriverFromSession(new SessionId(System.getenv("session_id")), new URL(System.getenv("url")));
//                    //driver2 = createDriverFromSession(session_id, url);
//                    driver = (WebDriver)driver2;
                }
                break;

            case "FF":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "EDGE-NP":
                WebRunSettings.builder = new ProcessBuilder("C:\\Program Files (x86)\\Microsoft\\Edge\\Application\\msedge.exe\" about:blank --new-window --remote-debugging-port=9222 --user-data-dir=C:\\Temp");
                try {
                    WebRunSettings.process = WebRunSettings.builder.start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                // wait 10 seconds
                System.out.println("Waiting");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                WebDriverManager.edgedriver().setup();

                EdgeOptions eOptions = new EdgeOptions();
                eOptions.setExperimentalOption("debuggerAddress", "localhost:9222");
                driver = new EdgeDriver(eOptions);
                break;

            case "EDGE":
                WebDriverManager.edgedriver().setup();

                eOptions = new EdgeOptions();
                driver = new EdgeDriver(eOptions);
                break;
            case "IE":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            case "CHI":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

        }

        driver.manage().window().maximize();
        driver.get(uRL);
        Reports.screenshot("Launch Browser and URL");


    }
    public static RemoteWebDriver createDriverFromSession(final SessionId sessionId, URL command_executor){
        CommandExecutor executor = new HttpCommandExecutor(command_executor) {


            public Response execute(Command command) {
                Response response = null;
                if (command.getName().equals("newSession")) {
                    response = new Response();
                    response.setSessionId(sessionId.toString());
                    response.setStatus(0);
                    response.setValue(Collections.<String, String>emptyMap());

                    try {
                        Field commandCodec = null;
                        commandCodec = this.getClass().getSuperclass().getDeclaredField("commandCodec");
                        commandCodec.setAccessible(true);
                        commandCodec.set(this, new W3CHttpCommandCodec());

                        Field responseCodec = null;
                        responseCodec = this.getClass().getSuperclass().getDeclaredField("responseCodec");
                        responseCodec.setAccessible(true);
                        responseCodec.set(this, new W3CHttpResponseCodec());
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }

                } else {
                    try {
                        response = super.execute(command);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                return response;
            }
        };

        return new RemoteWebDriver(executor, new DesiredCapabilities());
    }
}
