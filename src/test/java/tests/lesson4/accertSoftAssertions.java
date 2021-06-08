package tests.lesson4;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class accertSoftAssertions {
    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void softAssertionsShouldBe(){
        open("https://github.com");
        $("[name = q]").setValue("selenide").pressEnter();
        $("ul.repo-list li a").click();
        $("[data-content = Wiki]").click();
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $(byText("SoftAssertions")).click();
        $("body").shouldHave(text("JUnit5 extension - com.codeborne.selenide.junit5.SoftAssertsExtension"));
    }


    @Test
    void theInternetDragAndDropTest(){
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").dragAndDropTo("#column-b");
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }
}
