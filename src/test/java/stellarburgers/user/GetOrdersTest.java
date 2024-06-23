package stellarburgers.user;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import stellarburgers.steps.OrderSteps;
import java.net.HttpURLConnection;
import static org.hamcrest.CoreMatchers.is;

public class GetOrdersTest extends BaseUserTest{
    protected final OrderSteps orderSteps = new OrderSteps();

    @Test
    @DisplayName("Get orders")
    @Description("Positive test")
    public void getOrders(){
        userSteps.createUser(user);

        String accessToken = userSteps.loginUser(user).extract().path("accessToken");

        orderSteps.getOrdersAuth(accessToken)
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("success", is(true));
    }
}
