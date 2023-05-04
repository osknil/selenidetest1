package ltuselenide;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Screenshots.takeScreenShotAsFile;
import static com.codeborne.selenide.WebDriverRunner.source;
import static com.codeborne.selenide.files.DownloadActions.click;


public class LtuMainTest {
    LtuMain ltuMain = new LtuMain();

    @Test
    public void login() {
        System.setProperty("selenide.holdBrowserOpen", "true");
        open("http://ltu.se");
        ltuMain.acceptCookies.click();
        ltuMain.linkMittLtu.click();
        LoginCred loginCred = new LoginCred();
        ltuMain.initiateLogin.click();
        ltuMain.inputUsername.sendKeys(loginCred.getEmail());
        ltuMain.inputPassword.sendKeys(loginCred.getPassword());
        ltuMain.finishLogin.click();
    }

    /*@Test
    public void fetchCertificate() {
        ltuMain.certificateButton.click();
        switchTo().window(1);
        ltuMain.buttonUnderstand.click();
        ltuMain.accessStudentPortal.click();
        ltuMain.institutionTextField.sendKeys("Luleå Tekniska Universitet");
        ltuMain.selectInstitution.shouldBe(Condition.visible).click();
        ltuMain.dropdownMenu.shouldBe(Condition.visible).click();
        ltuMain.studentTranscript.click();
    }*/
    /*@Test
    public void createCertificate() {
        ltuMain.createTranscript.click();
        ltuMain.selectTypeOfTranscript.click();
        ltuMain.transcriptOfRecords.shouldBe(Condition.visible).click();
        ltuMain.inputPassedModules.shouldBe(Condition.visible).click();
        ltuMain.buttonCreate.click();
    }

    @Test
    public void getCertificate(){
    ltuMain.downloadTranscript.click();
        File pdfFile = null;
        try {
            pdfFile = ltuMain.downloadTranscript.download();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Path source = pdfFile.toPath();
        Path targetDir = Paths.get("C:\\Users\\User\\JetBrainsProjects\\selenidetest1\\target\\files");
        Path target = targetDir.resolve(source.getFileName());

        try {
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
*/
    @Test
    public void finalExam() {
        ltuMain.dropDownExam.click();
        ltuMain.examSchedule.shouldBe(Condition.visible).click();
        switchTo().window(1);
        ltuMain.textFieldSearchExam.click();
        ltuMain.textFieldSearchExam.sendKeys("I0015N");
        ltuMain.searchExam.click();
        ltuMain.examInfo.shouldBe(Condition.visible).click();
        switchTo().window(2);
    }
    /*  @Test
        public void takeScreenshot(){
        Path path = Paths.get("C:/Users/User/JetBrainsProjects/selenidetest1/target/files/screenshots");
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Take a screenshot and save it to the directory
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String filename = "screenshot-" + now.format(formatter) + ".jpeg";
        File screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(screenshot.toPath(), Paths.get(path.toString(), filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
    @Test
    public void courseSyllabus() {

    /*    ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", "C:/Users/User/JetBrainsProjects/selenidetest1/target/files/downloads/");
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
*/
      //  Configuration.fileDownload = FileDownloadMode.FOLDER;
        Configuration.downloadsFolder = ("C:/Users/User/JetBrainsProjects/selenidetest1/target/files/downloads/");
        switchTo().window(0);
        ltuMain.dropDownCourses.click();
        ltuMain.searchEducation.shouldBe(Condition.visible).click();
        switchTo().window(3);
        if(ltuMain.acceptCookies.exists())
            ltuMain.acceptCookies.shouldBe(Condition.visible).click();

        ltuMain.textFieldSearchCourse.click();
        ltuMain.textFieldSearchCourse.sendKeys("i0015n");
        ltuMain.searchCourse.shouldBe(Condition.visible).click();
        ltuMain.selectCourse.shouldBe(Condition.visible).click();
        ltuMain.linkSyllabus.shouldBe(Condition.visible).click();
        ltuMain.downloadSyllabus.click();


        File pdfFile = null;
        try {
            pdfFile = ltuMain.downloadSyllabus.download();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Path source = pdfFile.toPath();
        Path targetDir = Paths.get("C:\\Users\\User\\JetBrainsProjects\\selenidetest1\\target\\files\\downloads");
        Path target = targetDir.resolve(source.getFileName());

        try {
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}