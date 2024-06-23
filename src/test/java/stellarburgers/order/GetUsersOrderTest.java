package stellarburgers.order;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import stellarburgers.steps.OrderSteps;
import stellarburgers.user.BaseUserTest;

import java.net.HttpURLConnection;
import static org.hamcrest.CoreMatchers.equalTo;
import static stellarburgers.constants.ResponseMessages.UNAUTHORISED_USER_ERROR;

public class GetUsersOrderTest extends BaseOrderTest {
    protected final OrderSteps orderSteps = new OrderSteps();

    @Test
    @DisplayName("Get orders for certain user")
    @Description("Negative test for /api/orders endpoint")
    public void getUserOrdersUnauthorised(){
        orderSteps.getOrdersUnAuth()
                .statusCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .body("message", equalTo(UNAUTHORISED_USER_ERROR));

    }
}
