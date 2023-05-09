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
public class DownloadTranscript {
    Elements elements = new Elements();

    // Test case for logging in
    @Test
    @Order(1)
    public void alogin() {
        System.setProperty("selenide.holdBrowserOpen", "true");
        Configuration.startMaximized = true;
        open("https://ltu.se");

        // Accepting cookies
        elements.acceptCookies.click();

        // Clicking on the link to "Mitt LTU"
        elements.linkMittLtu.click();

        // Retrieving login credentials
        LoginCred loginCred = new LoginCred();

        // Initiating the login process
        elements.initiateLogin.click();

        // Entering the username and password
        elements.inputUsername.sendKeys(loginCred.getEmail());
        elements.inputPassword.sendKeys(loginCred.getPassword());

        // Logging in
        elements.finishLogin.click();
    }

    // Test case for downloading the transcript
    @Test
    @Order(2)
    public void getCertificate() {
        // Clicking on the certificate button
        elements.certificateButton.click();

        // Switching to the new window
        switchTo().window(1);

        // Clicking on the "Understand" button
        elements.buttonUnderstand.click();

        // Accessing the student portal
        elements.accessStudentPortal.click();

        // Entering the institution name
        elements.institutionTextField.sendKeys("Luleå Tekniska Universitet");

        // Selecting the institution from the dropdown
        elements.selectInstitution.shouldBe(Condition.visible).click();

        // Checking if a dropdown menu exists and clicking on it
        if (elements.dropdownMenu.exists())
            elements.dropdownMenu.shouldBe(Condition.visible).click();

        // Clicking on the student transcript option
        elements.studentTranscript.click();

        // Configuring the file download settings
        Configuration.fileDownload = FileDownloadMode.FOLDER;
        Configuration.downloadsFolder = ("C:/Users/User/JetBrainsProjects/selenidetest1/target/files/downloads/");

        // Clicking on the download transcript button
        elements.downloadTranscript.click();

        // Downloading the PDF file
        File pdfFile = null;
        try {
            pdfFile = elements.downloadTranscript.download();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Moving the downloaded file to the target directory
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
