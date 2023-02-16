package objectRepository;

public class CommonObjects {

    public static String txtUserName="xpath=//input[@id='usernamefield']";
    public static String txtPassword="xpath=//input[@id='passwordfield']";
    public static String btnLoginButton="xpath=//button[@id='loginButton']";
    public static String linkExitConfiguration="xpath=//a[text()=' Exit Configuration']";
    public static String linkProfile="xpath=//a[contains(@class,'dropdown-toggle lighterblue open-sans')]";
    public static String linkLogout="xpath=//li[text()='Log Out']/parent::*";
    public static String ddlClientSelection="xpath=//select[@id='client_selection']";
    public static String ddlClientSelectionOnConfiguratation="xpath=//select[@class='blue-select p-l-xs m-r f14 fw-600 bg-trans lighterblue no-border ng-pristine ng-untouched ng-valid ng-isolate-scope']";
    public static String linkConfiguration="xpath=//a[text()='Configuration']";
}
