package seminar1.sem;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seminar1.AccuweatherAbstractTest;

import javax.swing.plaf.synth.Region;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRegionListTest extends AccuweatherAbstractTest {
    @Test
    void getRegions(){

        List<Region> result = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/locations/v1/regions")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .body().jsonPath().getList(".", Region.class);

        Assertions.assertEquals(10, result.size());;
    }

}
