package ru.netology.allure.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.allure.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class ReplanDeliveryTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @BeforeAll
    public static void setUpAll(){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    public static void tearDownAll(){
        SelenideLogger.removeListener("allure");
    }

    @Test
    public void shouldHappyPathReplanDelivery() {
        var validUser = DataGenerator.Registration.generateUser("ru");

        SelenideElement form = $("form");
        form.$("[data-test-id='city'] input")
                .setValue(validUser.getCity());
        form.$("[data-test-id='date'] input")
                .press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE)
                .setValue(DataGenerator.generateDate(3));
        form.$("[data-test-id='name'] input")
                .setValue(validUser.getName());
        form.$("[data-test-id='phone'] input")
                .setValue(validUser.getPhone());
        form.$("[data-test-id='agreement']")
                .click();
        form.$(".grid-row button")
                .click();
        $("[data-test-id='success-notification'] .notification__content")
                .should(Condition.visible, Duration.ofSeconds(15))
                .should(Condition.exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(3)));
        form.$("[data-test-id='date'] input")
                .press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE)
                .setValue(DataGenerator.generateDate(7));
        form.$(".grid-row button")
                .click();
        $("[data-test-id='replan-notification'] button")
                .click();
        $("[data-test-id='success-notification'] .notification__content")
                .should(Condition.visible, Duration.ofSeconds(15))
                .should(Condition.exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(7)));
    }
}
