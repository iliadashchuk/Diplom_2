package stellarburgers.user;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import java.net.HttpURLConnection;
import static org.hamcrest.CoreMatchers.*;
import static stellarburgers.constants.ResponseMessages.UNAUTHORISED_USER_ERROR;
import static stellarburgers.constants.ResponseMessages.USER_EMAIL_CONFLICT;

public class UpdateUserTest extends BaseUserTest {

    @Test
    @DisplayName("Update. User authorised")
    @Description("Positive test for /api/auth/user endpoint")
    public void authorisedUpdatingUserTest(){
        userSteps.createUser(user);

        String accessToken = userSteps.loginUser(user)
                .extract()
                .body()
                .path("accessToken");

        user.setAccessToken(accessToken.substring(7));
        user.setEmail(RandomStringUtils.randomAlphabetic(6, 10) + "@yandex.ru");
        user.setPassword(RandomStringUtils.randomAlphabetic(6, 10));
        userSteps.updateUser(user)
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("success", is(true));
    }
    @Test
    @DisplayName("Update email")
    @Description("Positive test for /api/auth/user endpoint")
    public void updateEmailTest(){

        userSteps.createUser(user);

        String accessToken = userSteps.loginUser(user)
                .extract()
                .body()
                .path("accessToken");

        user.setEmail(RandomStringUtils.randomAlphabetic(6, 10) + "@yandex.ru");
        user.setAccessToken(accessToken.substring(7));
        userSteps.updateUser(user)
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("success", is(true));
    }
    @Test
    @DisplayName("Update password")
    @Description("Positive test for /api/auth/user endpoint")
    public void updatePasswordTest(){

        userSteps.createUser(user);

        String accessToken = userSteps.loginUser(user)
                .extract()
                .body()
                .path("accessToken");

        user.setPassword(RandomStringUtils.randomAlphabetic(6, 10));
        user.setAccessToken(accessToken.substring(7));
        userSteps.updateUser(user)
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("success", is(true));
    }
    @Test
    @DisplayName("Update already existed email")
    @Description("Negative test for /api/auth/user endpoint")
    public void updateExistedEmailTest(){

        userSteps.createUser(user);

        String accessToken = userSteps.loginUser(user)
                .extract()
                .body()
                .path("accessToken");
        String before = user.getEmail();
        user.setEmail("uINaeGRwL@yandex.ru");
        user.setAccessToken(accessToken.substring(7));
        userSteps.updateUser(user)
                .statusCode(HttpURLConnection.HTTP_FORBIDDEN)
                .body("message", equalTo(USER_EMAIL_CONFLICT));

        user.setEmail(before);
    }
    @Test
    @DisplayName("Update. User unauthorised")
    @Description("Negative test for /api/auth/user endpoint")
    public void unauthorisedUpdatingUserTest(){
        userSteps.updateUnauthorisedUser(user)
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .body("message", equalTo(UNAUTHORISED_USER_ERROR));
    }
}
