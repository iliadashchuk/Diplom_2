package stellarburgers.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import stellarburgers.constants.Configurations;
import stellarburgers.models.Order;

import static io.restassured.RestAssured.given;

public class OrderSteps {
    @Step
    public ValidatableResponse createOrder(Order order){
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post(Configurations.CREATE_ORDER)
                .then();
    }
    @Step
    public ValidatableResponse getOrdersAuth(String accessToken){
        return given().log().all()
                .headers("Authorization",
                        "Bearer " + accessToken.substring(7),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when()
                .get(Configurations.GET_USER_ORDERS)
                .then();
    }
    @Step
    public ValidatableResponse getOrdersUnAuth(){
        return given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .get(Configurations.GET_USER_ORDERS)
                .then();
    }
}
