package guru.qa.hw6;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class WebSteps {

    @Step("Переход на страницу: {url}")
    WebSteps openMainPage(String url){
        open(url);
        return this;
    }

    @Step("Ввод поискового запроса: {searchText}")
    WebSteps search(String searchText){
        $(".header-search-input").click();
        $(".header-search-input").setValue(searchText);
        $(".header-search-input").pressEnter();
        return this;
    }

    @Step("Переход в библиотеку Selenide")
    WebSteps goToLibrary(){
        $(byLinkText("selenide/selenide")).click();
        return this;
    }
    @Step("Переход на вкладку Issues")
    WebSteps goToIssueTab(){
        $("#issues-tab").click();
        return this;
    }

    @Step("Проверка видимости issue {issueNumber}")
    WebSteps checkVisibilityIssueNumber(int issueNumber){
        Allure.addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
        $(withText("#" + issueNumber)).shouldBe(visible);
        return this;
    }
}
