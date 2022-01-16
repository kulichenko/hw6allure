package guru.qa.hw6;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class AllureTests {
private static final String URL = "https://github.com/";

    @Test
    @Owner("makulich")
    @DisplayName("Test with listener")
    @Severity(SeverityLevel.BLOCKER)
    void selenideWithListenerTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open(URL);
        $(".header-search-input").click();
        $(".header-search-input").setValue("selenide");
        $(".header-search-input").pressEnter();
        $(byLinkText("selenide/selenide")).click();
        $("#issues-tab").click();
        $(withText("#1572")).shouldBe(visible);
    }

    @Test
    @Owner("makulich")
    @DisplayName("Test with lambda steps")
    @Severity(SeverityLevel.NORMAL)
    void selenideWithLambdaStepsTest() {
        step("Переход на главную страницу Github", () -> {
            open(URL);
        });
        step("Ввод поискового запроса", () -> {
            $(".header-search-input").click();
            $(".header-search-input").setValue("selenide");
            $(".header-search-input").pressEnter();
        });
        step("Переход в библиотеку Selenide", () -> {
            $(byLinkText("selenide/selenide")).click();
        });
        step("Переход на вкладку Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверка видимости issue 1572", () -> {
            Allure.addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
            $(withText("#1572")).shouldBe(visible);
        });
    }

    @Test
    @Owner("makulich")
    @DisplayName("Test with web steps")
    @Severity(SeverityLevel.CRITICAL)
    void selenideWithWebSteps(){
        WebSteps steps = new WebSteps();
        steps.openMainPage(URL)
                .search("selenide")
                .goToLibrary()
                .goToIssueTab()
                .checkVisibilityIssueNumber(1572);
    }
}
