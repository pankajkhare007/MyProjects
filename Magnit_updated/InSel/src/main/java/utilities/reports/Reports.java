package utilities.reports;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.web.WebDriverLib;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Properties;

public class Reports extends WebDriverLib {


    public static String a2Path;
    public static ExtentHtmlReporter html1;

    public static ExtentReports parentextent;
    public static ExtentTest childtest;
    public static ExtentTest test;
    public static int iTestStep = 1;
    public static MediaEntityBuilder meb;

    public static java.util.Date today = Calendar.getInstance().getTime();
    public static DateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    public static String variable = "Result" + dateformat.format(today);
    //System.getProperty("user.dir")
    public static File di = new File(String.format("C:/AutomationReports/CustomReports/%s", variable));
    public static boolean suc = di.mkdir();

    public static void startTestReport(String TestName) {

        String rPath = di + File.separator + "STMExtentReport.html";
        if (suc)
            html1 = new ExtentHtmlReporter(di);
        ExtentHtmlReporter html1 = new ExtentHtmlReporter(rPath);
        parentextent = new ExtentReports();
        parentextent.attachReporter(html1);
        html1.config().setDocumentTitle("Automation Report");
        html1.config().enableTimeline(true);
        html1.config().setReportName(TestName);

    }

    @Feature("{value}")
    public static void StartTest(String value) {
        test = parentextent.createTest(value);
        iTestStep = 1;
    }

    public static void startTestStep(String value) {

        childtest = test.createNode("Test Step " + iTestStep++ + ": " + value);

    }

    @Step("{value}")
    public static void testStep(String value) {

        if (test != null) {
            if (childtest == null) {
                test.log(Status.INFO, value);

            } else
                childtest.log(Status.INFO, value);
        }
    }

    /*
     * public static void Infosteps(String value) { Reports.test.info(value); }
     */
    public static void methodStarted(String value) {
        if (childtest == null) {
            test.log(Status.INFO, value);

        } else
            childtest.log(Status.INFO, value);
    }

    public static void flushProcess() {
        Reports.parentextent.flush();

    }

    public static String takeScreenShot(WebDriver driver) {
        java.util.Date today = Calendar.getInstance().getTime();
        DateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String filename = dateformat.format(today) + ".png";
        File SourceFile;
        try {
            SourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(SourceFile, new File(di + "/" + filename));
        } catch (Exception e) {

        }
        return filename;

    }

    public static void Verification(String actualValue, String details) {
        String vari1 = Reports.takeScreenShot(driver);
        try {
            meb = MediaEntityBuilder.createScreenCaptureFromPath(vari1);
        } catch (Exception e) {

        }
        if (actualValue.toLowerCase().equals("pass")) {
            if (Reports.childtest == null) {
                Reports.test.log(Status.PASS, details, meb.build());
            } else
                Reports.childtest.log(Status.PASS, details, meb.build());
        }

        if (actualValue.toLowerCase().equals("fail")) {
            if (Reports.childtest == null) {
                Reports.test.log(Status.FAIL, details, meb.build());
                //SoftAssert soft = new SoftAssert();
                // soft.assertEquals(Status.FAIL, "Details");
                Assertions.fail(details);



            } else {
                Reports.childtest.log(Status.FAIL, details, meb.build());
                //SoftAssert soft = new SoftAssert();
                // soft.assertEquals(Status.FAIL, "Details");
                Assertions.fail(details);
            }

        }
    }


    public static void moveAllureResult() {

        DateTimeFormatter dtn = DateTimeFormatter.ofPattern("DDMMYYHHMMSS");
        LocalDateTime now = LocalDateTime.now();
        String datetime = "Result" + dtn.format(now);
        String aPath = System.getProperty("user.dir") + "\\allure-results\\";
        File f1 = new File(aPath);
        // File filesList[] = f1.listFiles();
        //System.getProperty("user.dir")+
        a2Path = "C:\\AutomationReports\\AllureReports\\" + datetime;
        File aalureDi = new File(String.format("C:/AutomationReports/AllureReports"));
        aalureDi.mkdir();
        File f2 = new File(a2Path);
        boolean newfolder = f2.mkdir();
        if (newfolder) {
            try {
                Files.move(new File(aPath).toPath(), new File(a2Path).toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        File batchf = new File(a2Path + "\\Reports.bat");

        try {
            FileOutputStream fos = new FileOutputStream(batchf);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeBytes("cmd /k allure serve " + a2Path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        removeParametersInReport(new File(a2Path));

    }

    //@Attachment(value = "{Report}", type = "image/png")
    public static byte[] screenshot(String Report) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    public static void sendReportTORecipients(String emailAddress) {
        Properties props = new Properties();
        // this will set host of server- you can change based on your requirement
        props.put("mail.smtp.host", "smtp.gmail.com");
        // set the port of socket factory
        props.put("mail.smtp.socketFactory.port", "587");
        // set socket factory
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // set the authentication to true
        props.put("mail.smtp.auth", "true");
        // set the port of SMTP server
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.enable", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        // This will handle the complete authentication
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("your email", "your password");
                    }

                }
        );
        try {

            // Create object of MimeMessage class
            Message message = new MimeMessage(session);

            // Set the from address
            message.setFrom(new InternetAddress("kt110287@gmail.com"));

            // Set the recipient address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress));

            // Add the subject link
            message.setSubject("Testing Subject");

            // Create object to add multimedia type content
            BodyPart messageBodyPart1 = new MimeBodyPart();

            // Set the body of email
            messageBodyPart1.setText("This is message body");

            // Create another object to add another content
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();

            // Mention the file which you want to send
            String filename = di.toString();

            // Create data source and pass the filename
            DataSource source = new FileDataSource(filename);

            // set the handler
            messageBodyPart2.setDataHandler(new DataHandler(source));

            // set the file
            messageBodyPart2.setFileName(filename);

            // Create object of MimeMultipart class
            Multipart multipart = new MimeMultipart();

            // add body part 1
            multipart.addBodyPart(messageBodyPart2);

            // add body part 2
            multipart.addBodyPart(messageBodyPart1);

            // set the content
            message.setContent(multipart);

            // finally send the email
            Transport.send(message);

            System.out.println("=====Email Sent=====");

        } catch (MessagingException e) {

            throw new RuntimeException(e);

        }


    }

    public static void removeParametersInReport(File dir) {
        //File dir = new File(System.getProperty("user.dir") + "/allure-results");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                if (child.getName().contains("result")) {
                    removeParameterInJson(child);
                }
            }
        } else {

        }
    }

    private static void removeParameterInJson(File fileToBeUpdated) {
        try {
            FileReader reader = new FileReader(fileToBeUpdated);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            String tStart = jsonObject.get("start").toString();
            String tStop = jsonObject.get("stop").toString();
            String mname = jsonObject.get("testCaseName").toString();

            long tDur = Long.parseLong(tStop) - Long.parseLong(tStart);
            reader.close();
            if(tDur < 100) fileToBeUpdated.delete();
            else {
                jsonObject.put("name",mname);

                FileWriter wFile = new FileWriter(fileToBeUpdated);
                wFile.write(jsonObject.toJSONString());
                wFile.close();
                wFile.flush();

            }




        } catch (NullPointerException | IOException | ParseException e) {

        }

    }

}
