package stellarburgers.user;

import io.restassured.RestAssured;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import stellarburgers.models.User;
import stellarburgers.steps.UserSteps;
import static stellarburgers.constants.Configurations.SITE_URL;

public class BaseUserTest {
    protected final UserSteps userSteps = new UserSteps();
    protected User user;

    @Before
    public void setUp(){
        RestAssured.baseURI = SITE_URL;

        user = new User();
        user.setEmail(RandomStringUtils.randomAlphabetic(6, 10) + "@yandex.ru");
        user.setPassword(RandomStringUtils.randomAlphabetic(6, 10));
        user.setName(RandomStringUtils.randomAlphabetic(6, 10));
    }

    @After
    public void tearDown(){
        String accessToken = userSteps.loginUser(user)
                .extract()
                .body()
                .path("accessToken");
        user.setAccessToken(accessToken);
        if(accessToken != null){
            user.setAccessToken(accessToken.substring(7));
            userSteps.deleteUser(user);
        }
    }
}
