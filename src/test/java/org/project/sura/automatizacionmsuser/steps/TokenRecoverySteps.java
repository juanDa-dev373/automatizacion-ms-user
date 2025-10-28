package org.project.sura.automatizacionmsuser.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.project.sura.automatizacionmsuser.context.TestContext;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenRecoverySteps {

    private String baseUrl;
    private Response response;

    @Given("la API esta disponible en {string}")
    public void la_api_esta_disponible_en(String url) {
        baseUrl = url;
        RestAssured.baseURI = baseUrl;
    }

    @When("realiza una peticion POST a {string}")
    public void realiza_peticion(String endpoint){
        String username = TestContext.getUsername();
        String body = String.format("""
                {
                    "username": "%s"
                }
                """, username);
        response = RestAssured.given()
                .contentType("application/json")
                .when()
                .body(body)
                .post(endpoint);
    }

    @Then("debe tener codigo de respuesta {int}")
    public void  debe_tener_codigo_de_respuesta(Integer status){
        response.then().log().ifError();
        assertEquals(status, response.getStatusCode());
    }

    @Then("debe contener el campo {string} para recuperacion")
    public void debe_contener_el_campo_para_recuperacion(String token){
        TestContext.setTokenRecovery(response.jsonPath().getString(token));
        assertNotNull(response.then().body("$", hasKey(token)));
    }

}
