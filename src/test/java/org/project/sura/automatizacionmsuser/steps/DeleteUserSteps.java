package org.project.sura.automatizacionmsuser.steps;

import groovy.util.logging.Slf4j;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.project.sura.automatizacionmsuser.context.TestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class DeleteUserSteps {

    private static final String BASE_URL = System.getProperty("api.url", "http://localhost:8083");

    private Response response;

    @Given("la API para eliminar usuario esta disponible en {string}")
    public void eliminar_usuario(String url){
        System.out.println("Url del micro: " + BASE_URL);
        RestAssured.baseURI = BASE_URL;
    }

    @When("se realiza solicitud DELETE a {string}")
    public void realiza_solicitud_delete(String endpoint){
        Long id = TestContext.getId();
        String token = TestContext.getToken();
        response = given()
                .when()
                .pathParam("id", id)
                .header("Authorization", "Bearer " + token)
                .delete(endpoint);
    }

    @Then("debe devolver codigo de respuesta {int}")
    public void status_response_200(Integer status){
        assertEquals(status, response.getStatusCode());
    }


}
