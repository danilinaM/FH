package org.example.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.То;
import io.restassured.response.Response;
import org.example.testdata.TestData;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiSteps {

    private final AuthHelper authHelper = new AuthHelper();
    private Response lastResponse;

    static {
        // Настраиваем baseURI для RestAssured
        baseURI = "https://lk.fitnesshouse.ru";
    }

    @Дано("получаем токен testUser")
    public void getTokenTestUser() {
        authHelper.getTokenWithApi();
        authHelper.authorizeUser();
        authHelper.getLaravelToken();
        authHelper.refreshAuthToken();
    }

    @Когда("отправляем GET запрос на {string}")
    public void sendGETRequest(String endpoint) {
        lastResponse = given()
                .header("X-CSRF-TOKEN", TestData.csrfTokenFromMeta)
                .cookie("laravel_token", TestData.laravel_token)
                .log().all()
                .get(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    @То("получаем данные пользователя:")
    public void getUserData(DataTable dataTable) {
        getAnswerInTable(dataTable);
    }

    private void getAnswerInTable(DataTable dataTable) {
        List<Map<String, String>> expectedData = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> row : expectedData) {
            String field = row.get("поле");
            String expectedValue = row.get("значение");

            switch (field) {
                case "статус":
                    assertEquals(Integer.parseInt(expectedValue), lastResponse.getStatusCode());
                    break;
                case "тип контента":
                    lastResponse.then().header("Content-Type", expectedValue);
                    break;
                default:
                    // Проверяем JSON поля
                    if (expectedValue.equals("true") || expectedValue.equals("false")) {
                        lastResponse.then().body(field, equalTo(Boolean.parseBoolean(expectedValue)));
                    } else if (expectedValue.matches("\\d+") && !field.equals("phone")) {
                        lastResponse.then().body(field, equalTo(Integer.parseInt(expectedValue)));
                    } else if (expectedValue.isEmpty()) {
                        lastResponse.then().body(field, anyOf(equalTo(""), equalTo(null)));
                    } else {
                        lastResponse.then().body(field, equalTo(expectedValue));
                    }
                    break;
            }
        }
    }
}
