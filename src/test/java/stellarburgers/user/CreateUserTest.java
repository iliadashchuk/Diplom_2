package stellarburgers.user;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import java.net.HttpURLConnection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static stellarburgers.constants.ResponseMessages.CREATE_USER_CONFLICT;
import static stellarburgers.constants.ResponseMessages.USER_ALREADY_EXIST;

public class CreateUserTest extends BaseUserTest{

    @Test
    @DisplayName("Create unique user")
    @Description("Positive test for /api/auth/register endpoint")
    public void createUser(){
        userSteps.createUser(user)
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("accessToken", notNullValue());
    }
    @Test
    @DisplayName("Create already exist user")
    @Description("Negative test for /api/auth/register endpoint")
    public void createUserWithExistedCredentials(){
        userSteps.createUser(user);
        userSteps.createUser(user)
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN)
                .body("message", equalTo(USER_ALREADY_EXIST));
    }
    @Test
    @DisplayName("Create user without email")
    @Description("Negative test for /api/auth/register endpoint")
    public void createUserWithoutEmail(){
        user.setEmail("");

        userSteps.createUser(user)
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN)
                .body("message", equalTo(CREATE_USER_CONFLICT));
    }
    @Test
    @DisplayName("Create user without password")
    @Description("Negative test for /api/auth/register endpoint")
    public void createUserWithoutPassword(){
        user.setPassword("");

        userSteps.createUser(user)
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN)
                .body("message", equalTo(CREATE_USER_CONFLICT));
    }
    @Test
    @DisplayName("Create user without name")
    @Description("Negative test for /api/auth/register endpoint")
    public void createUserWithoutName(){
        user.setName("");

        userSteps.createUser(user)
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN)
                .body("message", equalTo(CREATE_USER_CONFLICT));
    }
}
