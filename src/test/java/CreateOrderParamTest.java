import config.Config;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import models.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.OrdersStep;

import java.util.List;


import static org.hamcrest.Matchers.notNullValue;

@Feature("Создание заказа /api/v1/orders")
@RunWith(Parameterized.class)
public class CreateOrderParamTest {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color;

    public CreateOrderParamTest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime,
                                        String deliveryDate, String comment, List<String> color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters(name = "Testdata: {0}, {1}, {8}")
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Анна", "Кот", "Победы, 12", "ВДНХ", "89022222222", 1, "15-04-2023",
                        "срочно", List.of("Black")},
                {"Антон", "Антонов", "Почтовая 10", "Рижская", "+79151111111", 2, "02-05-2023",
                        "Спасибо!", List.of("Gray")},
                {"Иван", "Иванов", "Молодежная2 к 1", "Курская", "89013333333", 3, "02-06-2023",
                        "Жду", List.of("Black", "Gray")},
                {"Татьяна", "Петрова", "Театральная 3", "Савеловская", "+79014444444", 4, "21-04-2023",
                        "Можно не перезванивать", null},
        };
    }

    @Test
    @DisplayName("Cоздание заказа с валидными данными")
    public void successfullyCreateOrderWitchValidDataTest() {
        Order order = new Order(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        ValidatableResponse response = OrdersStep.createOrder(order);
        response.statusCode(201)
                .assertThat()
                .body("track", notNullValue());
    }
}