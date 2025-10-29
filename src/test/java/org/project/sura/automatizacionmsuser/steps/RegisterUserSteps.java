package org.project.sura.automatizacionmsuser.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.project.sura.automatizacionmsuser.context.DataGenerator;
import org.project.sura.automatizacionmsuser.context.TestContext;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RegisterUserSteps {

    private String baseUrl;
    private Response response;
    private final ObjectMapper mapper = new ObjectMapper();

    @Given("la API está disponible en {string}")
    public void la_api_esta_disponible_en(String url) {
        baseUrl = System.getProperty("api.url", url);
        RestAssured.baseURI = baseUrl;
    }

    @When("realizo una solicitud POST a {string} con el cuerpo")
    public void realizo_una_solicitud_post_a_con_cuerpo(String endpoint) throws JsonProcessingException {
        Map<String, Object> userData = DataGenerator.generateUserData();
        TestContext.setPassword(userData.get("password").toString());
        String body = mapper.writeValueAsString(userData);
        response = given()
                .contentType("application/json")
                .when()
                .body(body)
                .post(endpoint);
    }

    @Then("la respuesta debe tener código {int}")
    public void la_respuesta_debe_tener_codigo(Integer statusCode) {
        response.then().log().ifError();
        assertEquals(statusCode.intValue(), response.getStatusCode());
    }

    @Then("el cuerpo debe contener el campo {string}")
    public void el_cuerpo_debe_contener_el_campo(String campo){
        TestContext.setUsername(response.jsonPath().getString(campo));
        TestContext.setId(response.jsonPath().getLong("id"));
        assertNotNull(response.then().body("$", hasKey(campo)));
    }

}
