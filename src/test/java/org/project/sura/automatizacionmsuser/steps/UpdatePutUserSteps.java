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

public class UpdatePutUserSteps {

    private String baseUrl;
    private Response response;

    @Given("la API para el metodo put esta disponible en {string}")
    public void api_disponible(String url){
        baseUrl = url;
        RestAssured.baseURI = baseUrl;
    }

    @When("realizo una solicitud PUT a {string}")
    public void realizo_solicitud_put_a(String endpoint){
        String body = """
               {
                  "firstname": "string",
                  "lastname": "string",
                  "country": "string",
                  "newPassword": "string"
               }
        """;
        String token = TestContext.getToken();
        Long id = TestContext.getId();
        response = given()
                .contentType("application/json")
                .when()
                .pathParam("id", id)
                .header("Authorization", "Bearer " + token)
                .body(body)
                .put(endpoint);
    }

    @Then("la respuesta del update debe ser {int}")
    public void respuesta_update(Integer status){
        assertEquals(status, response.getStatusCode());
    }

    @Then("debe contener el campo {string}")
    public void debe_contener_el_campo(String campo){
        assertNotNull(response.then().body("$", hasKey(campo)));
    }



}
