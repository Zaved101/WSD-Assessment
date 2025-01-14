package TestCases;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITests {

    @Test
    public void testGetProducts() {
        RestAssured.baseURI = "https://magento.softwaretestingboard.com/rest/V1";

        int statusCode = RestAssured
                .get("/products")
                .getStatusCode();

        Assert.assertEquals(statusCode, 200, "API did not return a successful response.");
    }
}
