package ltuselenide;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FinalExamination {

    Elements elements = new Elements();  // creates an object of the Elements class

    @Test
    @Order(1)
    public void login() { // Log in functionality onto to LTUs webpage.

        // sets selenide.holdBrowserOpen property to true and opens LTU website
        System.setProperty("selenide.holdBrowserOpen", "true");
        Configuration.startMaximized = true;
        open("https://ltu.se");

        // clicks on "Accept Cookies" button and "Mitt LTU" link, and logs in with login credentials
        elements.acceptCookies.shouldBe(Condition.visible).click();
        elements.linkMittLtu.shouldBe(Condition.visible).click();
        LoginCred loginCred = new LoginCred();
        elements.initiateLogin.shouldBe(Condition.visible).click();
        elements.inputUsername.sendKeys(loginCred.getEmail());
        elements.inputPassword.sendKeys(loginCred.getPassword());
        elements.finishLogin.shouldBe(Condition.visible).click();
    }

    @Test
    @Order(2)
    public void finalExam() {
        // clicks on "Exam Schedule" link, searches for a specific exam, and opens its info in a new window
        elements.dropDownExam.shouldBe(Condition.visible).click();
        elements.examSchedule.shouldBe(Condition.visible).click();
        switchTo().window(1);
        elements.textFieldSearchExam.shouldBe(Condition.visible).click();
        elements.textFieldSearchExam.sendKeys("I0015N");
        elements.searchExam.shouldBe(Condition.visible).click();
        elements.examInfo.shouldBe(Condition.visible).click();
        switchTo().window(2);
    }

    @Test
    @Order(3)
    public void takeScreenshot() {

        // creates a directory to store the screenshot and takes a screenshot of the current window
        Path path = Paths.get("C:/Users/User/JetBrainsProjects/selenidetest1/target/files/screenshots");
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
}
