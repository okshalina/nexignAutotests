package MainPage;
import com.codeborne.selenide.Selenide;
import junit.framework.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import utils.BaseTest;

import java.io.IOException;
import java.util.List;
import org.languagetool.JLanguageTool;
import org.languagetool.language.Russian;
import org.languagetool.language.BritishEnglish;

import org.languagetool.rules.RuleMatch;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class MainPageTests extends BaseTest {

    private static final String xpathStore = "//*[@id='block-menyusverkhunewru']/ul/li[3]";
    private static final String xpathDevProducts = "//*[@id='block-menyusverkhunewru']/ul/li[3]/ul/li[1]/a";
    private static final String xpathDevProductsAsideMenu = "/html/body/div[1]/main/article/div/aside";
    private static final String xpathPersonalAccount = "//*[@id='block-menyusverkhunewru']/ul/li[3]/ul/li[2]/a";
    private static final String xpathAboutCompany = "//*[@id='block-menyusverkhunewru']/ul/li[4]";
    private static final String xpathAboutCompanyDropDownMenu = "//*[@id='block-menyusverkhunewru']/ul/li[4]/ul";


    public void checkSpelling() throws IOException {
        String pageText = $("body").text();

//        // For English
        JLanguageTool langToolEN = new JLanguageTool(new BritishEnglish(), null);
        List<RuleMatch> matchesEN = langToolEN.check(pageText);
        for (RuleMatch match : matchesEN) {
            System.out.println("Misspelling (EN) on position " +
                    match.getFromPos() + "-" + match.getToPos() + ": " +
                    match.getMessage());
        }

        // For Russian
        JLanguageTool langToolRU = new JLanguageTool(new Russian(), null);
        List<RuleMatch> matchesRU = langToolRU.check(pageText);
        for (RuleMatch match : matchesRU) {
            System.out.println("Misspelling (RU) on position " +
                    match.getFromPos() + "-" + match.getToPos() + ": " +
                    match.getMessage());
        }
    }


    @DisplayName("Task 1")
    @Test
    public void clickThroughTest() {

        // Switch to "Store"
        $(By.xpath(xpathStore)).click();

        // Check drop-down menu
        $(By.xpath(xpathDevProducts)).shouldBe(visible.because("No dev products in drop-down menu"));
        $(By.xpath(xpathPersonalAccount)).shouldBe(visible.because("No link to personal account in drop-down menu"));

        // Switch to "Продукты для разработчиков"
        $(By.xpath(xpathDevProducts)).click();

        // Check page switching
        $("h1").shouldHave(text("Nexign Store"));
        $(By.xpath(xpathDevProductsAsideMenu)).shouldBe(visible.because("Aside menu"));
    }

    @DisplayName("Task 2")
    @Test
    public void nordSearchTest() {

        // Count word "Nord" in non-register-dependent mode
        long count = Selenide.$("body").text().split("(?i)Nord").length - 1;

        // Print result
        System.out.println("'Nord' count: " + count);

        // As a test
        Assert.assertEquals(1, count);
    }

    @DisplayName("Task 3")
    @Test
    public void checkSpellingTest() throws IOException {
        final String xpathAboutNexign = "//*[@id='block-menyusverkhunewru']/ul/li[4]/ul/li[1]/a";
        final String xpathNews = "//*[@id='block-menyusverkhunewru-2--2']/ul/li[2]/a";
        final String xpathSuccessStories = "//*[@id='block-menyusverkhunewru-2--2']/ul/li[3]/a";
        final String xpathEvents = "//*[@id='block-menyusverkhunewru-2--2']/ul/li[5]/a";
        final String xpathBlog = "//*[@id='block-menyusverkhunewru-2--2']/ul/li[6]/a";

        // Check spelling on main page
        checkSpelling();

        $(By.xpath(xpathAboutCompany)).click();
        $(By.xpath(xpathAboutCompanyDropDownMenu)).shouldBe(visible.because("No drop-down menu"));

        // Check spelling on page about Nexign
        $(By.xpath(xpathAboutNexign)).click();
        $("h1").shouldHave(text("О компании"));
        checkSpelling();

        // Check spelling on news page
        $(By.xpath(xpathNews)).shouldBe(visible.because("No aside menu"));
        $(By.xpath(xpathNews)).click();
        $("h1").shouldHave(text("Новости"));
        checkSpelling();

        // Check spelling on success stories page
        $(By.xpath(xpathSuccessStories)).shouldBe(visible.because("No aside menu"));
        $(By.xpath(xpathSuccessStories)).click();
        $("h1").shouldHave(text("Истории успеха"));
        checkSpelling();

        // Check spelling on events page
        $(By.xpath(xpathEvents)).shouldBe(visible.because("No aside menu"));
        $(By.xpath(xpathEvents)).click();
        $("h1").shouldHave(text("Конференции и выставки"));
        checkSpelling();

        // Check spelling on blog page
        $(By.xpath(xpathBlog)).shouldBe(visible.because("No aside menu"));
        $(By.xpath(xpathBlog)).click();
        $("h1").shouldHave(text("Блог"));
        checkSpelling();

    }
}
