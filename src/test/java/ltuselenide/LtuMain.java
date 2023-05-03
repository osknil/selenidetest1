package ltuselenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LtuMain {
    public SelenideElement initiateLogin = $x("//a[@class='button is-medium is-blue']");
    public SelenideElement inputUsername = $x("//input[@id='username']");
    public SelenideElement inputPassword = $x("//input[@id='password']");
    public SelenideElement finishLogin = $x("//input[contains(@name, 'submit')]");
    public SelenideElement acceptCookies = $ (By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"));
    public SelenideElement linkMittLtu = $x("//*[@id=\"main-nav\"]/div[3]/div/a[1]");
    public SelenideElement certificateButton = $x("//a[contains(@href, 'intyg')]");
    public SelenideElement buttonUnderstand = $x("//button[contains(@class, 'btn-light')]");
    public SelenideElement accessStudentPortal = $x("/html/body/ladok-root/div/main/div/ladok-inloggning/div/div/div/div/div/div/div/ladok-student/div[1]/a");
    public SelenideElement institutionTextField = $(By.id("searchinput"));
    public SelenideElement selectInstitution = $x("//*[@id=\"ds-search-list\"]/a");
    public SelenideElement dropdownMenu = $x("//button[@role='button']");
    public SelenideElement studentTranscript = $x("//*[@id=\"sidomeny-ul\"]/li[3]/ladok-behorighetsstyrd-nav-link/a");
    public SelenideElement createTranscript = $x("//*[@id=\"main\"]/div/ladok-intyg/ladok-skapa-intyg-knapp/div/button");
    public SelenideElement selectTypeOfTranscript = $x("//select[@id='intygstyp']");
    public SelenideElement transcriptOfRecords = $x("//option[@value='2: Object']");
    public SelenideElement inputPassedModules = $x("//*[@id='TaMedGodkandaModulerIAllaKurser']");
    public SelenideElement buttonCreate = $x("//button[contains(@class, 'me')]");
    public SelenideElement downloadTranscript = $x("//*[@id=\"main\"]/div/ladok-intyg/ladok-listning-av-skapade-intyg/div/div/ladok-accordion/div/ladok-list-kort[1]/div/div[1]/div/div[1]/a");
    public SelenideElement dropDownExam = $x("/html/body/div/div[1]/div[4]/div[1]/div[2]/div[2]/ul[2]/li[3]/a");
    public SelenideElement examSchedule = $x("/html/body/div/div[1]/div[4]/div[1]/div[2]/div[2]/ul[2]/li[3]/ul/li[2]/a");
    public SelenideElement textFieldSearchExam = $x("/html/body/div[1]/div[4]/div/div[2]/table/tbody/tr/td[1]/input");
    public SelenideElement searchExam = $x("//input[contains(@id, 'sokknapp')]");
    public SelenideElement examInfo = $x("/html/body/div[1]/div[4]/div/div[5]/ul/li[3]/table/tbody/tr/td[2]/a");
    public SelenideElement dropDownCourses = $x("/html/body/div/div[1]/div[4]/div[1]/div[2]/div[2]/ul[2]/li[6]/a");
    public SelenideElement searchEducation = $x("/html/body/div/div[1]/div[4]/div[1]/div[2]/div[2]/ul[2]/li[6]/ul/li[4]/a");
    public SelenideElement textFieldSearchCourse = $x("/html/body/main/div/div/div/div[2]/div/article[1]/section/form/p[4]/input");
    public SelenideElement searchCourse = $x("/html/body/main/div/div/div/div[2]/div/article[1]/section/form/p[6]/input[2]");
    public SelenideElement selectCourse = $x("/html/body/main/div/div/div/div[2]/div/div[2]/div[2]/div/h5/a");
    public SelenideElement linkSyllabus = $x("/html/body/main/div/div/div/div[1]/nav/div[1]/ul/li[1]/a");
    public SelenideElement downloadSyllabus = $x("/html/body/main/div/div/div/div[2]/div/article/div[1]/section/form/div[4]/a/div");

   ;


}





