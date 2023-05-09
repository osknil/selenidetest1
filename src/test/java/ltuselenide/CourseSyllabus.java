package ltuselenide;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CourseSyllabus {

    // Instantiate an instance of the Elements class
    Elements elements = new Elements();

    @Test
    @Order(1)
    public void login() {
        // Set configuration options
        System.setProperty("selenide.holdBrowserOpen", "true");
        Configuration.startMaximized = true;

        // Navigate to LTU website and perform login steps
        open("https://ltu.se");

        // Accept cookies if present
        if (elements.acceptCookies.exists())
            elements.acceptCookies.shouldBe(Condition.visible).click();

        elements.linkMittLtu.click();
        LoginCred loginCred = new LoginCred();
        elements.initiateLogin.click();
        elements.inputUsername.sendKeys(loginCred.getEmail());
        elements.inputPassword.sendKeys(loginCred.getPassword());
        elements.finishLogin.click();
    }

    @Test
    @Order(2)
    public void courseSyllabus() {
        // Set configuration options for file downloads
        Configuration.fileDownload = FileDownloadMode.FOLDER;
        Configuration.downloadsFolder = ("C:/Users/User/JetBrainsProjects/selenidetest1/target/files/downloads/");

        // Switch to the first window and navigate to the search page
        switchTo().window(0);
        elements.dropDownCourses.click();
        elements.searchEducation.shouldBe(Condition.visible).click();
        switchTo().window(1);

        // Accept cookies if present
        if (elements.acceptCookies.exists())
            elements.acceptCookies.shouldBe(Condition.visible).click();

        // Locate textfield and search for correct course
        elements.textFieldSearchCourse.shouldBe(Condition.visible).click();
        elements.textFieldSearchCourse.sendKeys("Test av IT-system");
        elements.searchCourse.shouldBe(Condition.visible).click();
        elements.selectCourse.shouldBe(Condition.visible).click();
        elements.linkSyllabus.shouldBe(Condition.visible).click();
        elements.downloadSyllabus.click();

        // Initiate PDF - File and set path for download while catching unexpected errors.
        File pdfFile = null;
        try {
            pdfFile = elements.downloadSyllabus.download();
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

        //Navigate back to the window with the "my-ltu homepage" and proceed to log out"
        switchTo().window(0);
        elements.logoutDropdown.click();
        elements.logoutButton.click();

    }
}
