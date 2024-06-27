package stellarburgers.order;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import stellarburgers.models.Order;

import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static stellarburgers.constants.ResponseMessages.CREATION_ORDER_CONFLICT;

public class CreateOrderTest extends BaseOrderTest {
    @Test
    @DisplayName("Create order")
    @Description("Positive test for /api/orders endpoint")
    public void createOrder(){
        List<String> ids = ingredientsSteps.getListOfIngredients()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract().path("data._id");
        Collections.shuffle(ids);
        order = new Order(List.of(ids.get(0), ids.get(1)));
        orderSteps.createOrder(order)
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("success", is(true));
    }
    @Test
    @DisplayName("Create order with invalid ingredients")
    @Description("Negative test for /api/orders endpoint")
    public void createOrderWithInvalidHash(){
        order = new Order(List.of("wrong", "wrong"));
        orderSteps.createOrder(order)
                .statusCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
    }
    @Test
    @DisplayName("Create order without hash ingredients")
    @Description("Negative test for /api/orders endpoint")
    public void createOrderWithoutIds(){
        order = new Order(List.of());
        orderSteps.createOrder(order)
               .statusCode(HttpURLConnection.HTTP_BAD_REQUEST)
               .body("message", equalTo(CREATION_ORDER_CONFLICT));
    }
}
