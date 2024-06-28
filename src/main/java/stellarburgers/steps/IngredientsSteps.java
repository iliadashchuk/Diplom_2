package stellarburgers.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static stellarburgers.constants.Configurations.INGREDIENTS_LIST;

public class IngredientsSteps {
    @Step
    public ValidatableResponse getListOfIngredients(){
         return given()
                .contentType(ContentType.JSON)
                .when()
                .get(INGREDIENTS_LIST)
                .then();
    }
}
