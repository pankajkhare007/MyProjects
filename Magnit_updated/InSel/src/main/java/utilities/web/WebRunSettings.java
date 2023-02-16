package utilities.web;

import org.junit.jupiter.api.TestInfo;

import java.util.Map;

public class WebRunSettings {

    public static TestInfo testInfo;


    public static String url ;
    public static String Browser;
    public static String newSession ;
    public static ProcessBuilder builder;
    public static Process process;

    public static String driverClassName;
    public static String dbUrl;
    public static String dbUsername;
    public static String dbPassword;

    public static String loginUsername;
    public static String loginPassword;
    public static Map<String, String> dataRow;
    public static String requestName;
    public static String requestID;
}
