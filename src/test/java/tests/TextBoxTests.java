package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests {

    @BeforeAll
    static void config(){
        Configuration.startMaximized = true;
    }

    @Test
    void succesfulSubmitForm(){
        String firstName = "Poseidon";
        String lastName = "King";
        String userEmail = "poseidon@greece.god";
        String userNumber = "9999999999";
        String subject = "Physics";
        String currentAddress = "Pacific Ocean";

        open("https://demoqa.com/automation-practice-form");
        $("[id=firstName]").setValue(firstName); // вводим имя
        $("[id=lastName]").setValue(lastName); // вводим фамилию
        $("[id=userEmail]").setValue(userEmail); // вводим почту
        $("[id=userNumber]").setValue(userNumber); // вводим номер телефона
        $("[id=subjectsInput]").setValue(subject).pressEnter(); // выбираем из списка
        $("[id=currentAddress]").setValue(currentAddress).pressEnter(); // вводим адрес
        $("[for=gender-radio-3]").click(); // идентифицируем себя как вертолет апач
        $("[for=hobbies-checkbox-1]").click();
            $("[for=hobbies-checkbox-2]").click();
                $("[for=hobbies-checkbox-3]").click(); // выбираем радиобаттоны
        $x("//input[@id=\"dateOfBirthInput\"]").click();
            $x("//select[@class=\"react-datepicker__year-select\"]//option[@value=\"1900\"][text()=\"1900\"]").click();
            $x("//select[@class=\"react-datepicker__month-select\"]//option[@value=\"0\"][text()=\"January\"]").click();
            $x("//div[@class=\"react-datepicker__month\"]//div[text()=1]").click(); // выбираем год, месяц, день в календаре
        $("#state").click();
            $(byText("Uttar Pradesh")).click();
            $("#city").click();
            $(byText("Agra")).click(); // выбираем штат и город из выпадающих списков
        $("#uploadPicture").uploadFile(new File("src/test/resources/harold.jpg")); // загружаем файл
        $("[id=submit]").click(); // кликаем на кнопку

            $(".table-responsive").shouldHave(
                    text("Student name"), text(firstName + ' ' + lastName)
                    ,text("Student Email"), text(userEmail)
                    ,text("Gender"), text("Other")
                    ,text("Mobile"), text(userNumber)
                    ,text("Date Of Birth"), text("01 January,1900")
                    ,text("Subject"), text(subject)
                    ,text("Address"), text(currentAddress)
                    ,text("Hobbies") ,text("Sports, Reading, Music")
                    ,text("Picture"), text("harold.jpg")
                    ,text("State and City"), text("Uttar Pradesh Agra")
            ); // проверяем форму



    }
}
