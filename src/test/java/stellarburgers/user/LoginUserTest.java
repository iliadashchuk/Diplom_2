package stellarburgers.user;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import java.net.HttpURLConnection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static stellarburgers.constants.ResponseMessages.LOGIN_USER_CONFLICT;

public class LoginUserTest extends BaseUserTest{

    @Test
    @DisplayName("Login user with correct credentials")
    @Description("Positive test for /api/auth/login endpoint")
    public void login(){
        userSteps.createUser(user);
        userSteps.loginUser(user)
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("accessToken", notNullValue());
    }
    @Test
    @DisplayName("Login user with incorrect email")
    @Description("Negative test for /api/auth/login endpoint")
    public void loginUserWithWrongEmail(){
        userSteps.createUser(user);
        String before = user.getPassword();

        user.setEmail("wrong");
        userSteps.loginUser(user)
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .body("message", equalTo(LOGIN_USER_CONFLICT));
        user.setPassword(before);
    }
    @Test
    @DisplayName("Login user with incorrect password")
    @Description("Negative test for /api/auth/login endpoint")
    public void loginUserWithWrongPassword(){
        userSteps.createUser(user);
        String before = user.getPassword();
        user.setPassword("wrong");
        userSteps.loginUser(user)
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .body("message", equalTo(LOGIN_USER_CONFLICT));
        user.setPassword(before);
    }
}
