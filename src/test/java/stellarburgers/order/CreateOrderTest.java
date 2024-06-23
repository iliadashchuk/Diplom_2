package stellarburgers.order;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import stellarburgers.models.Order;
import stellarburgers.steps.OrderSteps;

import java.net.HttpURLConnection;

import static org.hamcrest.CoreMatchers.*;
import static stellarburgers.constants.ResponseMessages.CREATION_ORDER_CONFLICT;

public class CreateOrderTest extends BaseOrderTest {
    @Test
    @DisplayName("Create order")
    @Description("Positive test for /api/orders endpoint")
    public void createOrder(){
        String[] correctIngredients = {"61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa6f"};
        order = new Order();
        order.setIngredients(correctIngredients);

        orderSteps.createOrder(order)
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("success", is(true));
    }
    @Test
    @DisplayName("Create order with invalid ingredients")
    @Description("Negative test for /api/orders endpoint")
    public void createOrderWithInvalidHash(){
        String[] wrongIngredients = {RandomStringUtils.randomAlphabetic(6, 10), RandomStringUtils.randomAlphabetic(6, 10)};
        order = new Order();
        order.setIngredients(wrongIngredients);

        orderSteps.createOrder(order)
                .statusCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
    }
    @Test
    @DisplayName("Create order without hash ingredients")
    @Description("Negative test for /api/orders endpoint")
    public void createOrderWithoutIds(){
        String[] wrongIngredients = {};
        order = new Order();
        order.setIngredients(wrongIngredients);

        orderSteps.createOrder(order)
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
                .body("message", equalTo(CREATION_ORDER_CONFLICT));
    }
}
