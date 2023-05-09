package ltuselenide;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MakeTranscript {

    Elements elements = new Elements();

    // Test case for logging in
    @Test
    @Order(1)
    public void alogin() { // Log in functionality onto to LTUs webpage.
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

    // Test case for creating a transcript
    @Test
    @Order(2)
    public void makeTranscript() {
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

        // Clicking on the "Create Transcript" button
        elements.createTranscript.shouldBe(Condition.visible).click();

        // Selecting the type of transcript
        elements.selectTypeOfTranscript.click();

        // Choosing the option for "Transcript of Records"
        elements.transcriptOfRecords.shouldBe(Condition.visible).click();

        // Enabling the input for all registrations
        elements.inputAllRegistrations.shouldBe(Condition.visible).click();

        // Selecting transcript inclusions
        elements.transcriptsInclusions.shouldBe(Condition.visible).click();

        // Selecting transcript language
        elements.transcriptLanguage.shouldBe(Condition.visible).click();

        // Clicking on the "Create" button to generate the transcript
        elements.buttonCreate.click();
    }
}
