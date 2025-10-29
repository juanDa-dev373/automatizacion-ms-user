package org.project.sura.automatizacionmsuser.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.project.sura.automatizacionmsuser.context.TestContext;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteUserSteps {

    private String baseUrl;
    private Response response;

    @Given("la API para eliminar usuario esta disponible en {string}")
    public void eliminar_usuario(String url){
        baseUrl = System.getProperty("api.url", url);
        RestAssured.baseURI = baseUrl;
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
