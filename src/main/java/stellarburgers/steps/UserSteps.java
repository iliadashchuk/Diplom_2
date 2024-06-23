package stellarburgers.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import stellarburgers.models.User;

import static io.restassured.RestAssured.given;
import static stellarburgers.constants.Configurations.*;

public class UserSteps {
    @Step
    public ValidatableResponse createUser(User user){
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(CREATE_USER)
                .then();
    }
    @Step
    public ValidatableResponse loginUser(User user){
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(LOGIN_USER)
                .then();
    }
    @Step
    public ValidatableResponse deleteUser(User user){
        return given().log().all()
                .headers("Authorization",
                        "Bearer " + user.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .body(user)
                .when()
                .delete("/api/auth/user")
                .then();
    }
    @Step
    public ValidatableResponse updateUser(User user){
        return given().log().all()
                .headers("Authorization",
                        "Bearer " + user.getAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .body(user)
                .when()
                .patch(USER_INFO)
                .then();
    }
    @Step
    public ValidatableResponse updateUnauthorisedUser(User user){
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .patch(USER_INFO)
                .then();
    }
}