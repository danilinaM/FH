package org.example.steps;

import com.codeborne.selenide.Configuration;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import io.restassured.response.Response;
import org.example.testdata.TestData;

import java.util.Map;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;

public class AuthHelper {

    @И("получаем CSRF токен для API")
    public void getTokenWithApi() {
            step("Get auth token to api and set it to next request", () -> {
                Configuration.browser = "chrome";
                Response response = given()
                        .contentType(JSON)
                        .when()
                        .get("/login/csrf-token")
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();

                TestData.csrfTokenFromMeta = response.htmlPath().getString("**.find { meta -> meta.@name == 'csrf-token' }.@content");
                TestData.sessionCookie = response.cookie("fitness_house_licnyi_kabinet_session");
            });
        }

    @И("авторизуемся через API")
    public void authorizeUser() {
        authorizeUser(TestData.phone, TestData.password);
    }

    @И("авторизуемся через API с параметрами")
    public void authorizeUser(DataTable dataTable) {
        Map<String, String> credentials = dataTable.asMap(String.class, String.class);
        String phone = credentials.get("phone");
        String password = credentials.get("password");

        System.out.println("Phone: " + phone);
        System.out.println("Password: " + password);

        authorizeUser(phone, password);
    }

    public void authorizeUser(String phone, String password) {
        step("Get login session cookie and set it for next requests", () -> {
            Response loginResponse = given()
                    .header("X-CSRF-TOKEN", TestData.csrfTokenFromMeta)
                    .cookie("fitness_house_licnyi_kabinet_session", TestData.sessionCookie)
                    .contentType(JSON)
                    .log().all()
                    .body(java.util.Map.of("phone", phone, "password", password))
                    .post("/login")
                    .then()
                    .log().all()
                    .statusCode(302)
                    .extract()
                    .response();

            System.out.println("Response body: " + loginResponse.getBody().asString());
            System.out.println("Response status: " + loginResponse.getStatusCode());
            System.out.println("Response headers: " + loginResponse.getHeaders());
            TestData.sessionCookie = loginResponse.cookie("fitness_house_licnyi_kabinet_session");
        });
    }
    
    @И("получаем Laravel токен")
    public void getLaravelToken() {
        step("Get lavarel token to api and set it to next request", () -> {
            Response response = given()
                    .cookie("fitness_house_licnyi_kabinet_session", TestData.sessionCookie)
                    .contentType(JSON)
                    .when()
                    .get(baseURI)
                    .then()
                    .log().all()
                    .statusCode(200)
                    .extract()
                    .response();

            TestData.laravel_token = response.cookie("laravel_token");
        });
    }
    
    @И("обновляем токен авторизации")
    public void refreshAuthToken() {
        step("Get new auth token to api and set it to next request", () -> {
            Response response2 = given()
                    .cookie("fitness_house_licnyi_kabinet_session", TestData.sessionCookie)
                    .cookie("laravel_token", TestData.laravel_token)
                    .contentType(JSON)
                    .when()
                    .get("/login/csrf-token")
                    .then()
                    .log().all()
                    .statusCode(200)
                    .extract()
                    .response();

            TestData.csrfTokenFromMeta = response2.htmlPath().getString("**.find { meta -> meta.@name == 'csrf-token' }.@content");
        });
    }

} 
