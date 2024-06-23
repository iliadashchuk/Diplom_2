package stellarburgers.order;
import io.restassured.RestAssured;
import org.junit.Before;
import stellarburgers.models.Order;
import stellarburgers.steps.OrderSteps;

import static stellarburgers.constants.Configurations.SITE_URL;

public class BaseOrderTest {
    protected final OrderSteps orderSteps = new OrderSteps();
    Order order;

    @Before
    public void setUp(){
        RestAssured.baseURI = SITE_URL;
    }
}