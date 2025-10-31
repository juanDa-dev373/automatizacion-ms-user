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

import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class LoginSteps {
    private static final Logger log = LoggerFactory.getLogger(LoginSteps.class);
    private String baseUrl;
    private Response response;

    @Given("la API de login está disponible en {string}")
    public void login(String url) {
        baseUrl = System.getProperty("api.url", url);
        System.out.println("Url del micro: " + baseUrl);
        RestAssured.baseURI = baseUrl;
    }

    @When("realizo una solicitud login POST a {string}")
    public void realizo_una_solicitud_login(String endpoint) {

        String username = TestContext.getUsername();
        String password = TestContext.getPassword();
        String body = String.format( """
                    {
                      "username": "%s",
                      "password": "%s"
                    }
                """, username, password);
        response = RestAssured.given()
                .contentType("application/json")
                .when()
                .body(body)
                .post(endpoint);

    }
    @Then("la respuesta de login debe tener código {int}")
    public void la_respuesta_debe_ser_200(Integer status){
        assertEquals(status,  response.statusCode());
    }

    @Then("el cuerpo de la solicitud de login debe contener el campo {string}")
    public void debe_contener_campo_token(String token){
        TestContext.setToken(response.jsonPath().getString(token), 15);
        assertNotNull(response.then().body("$", hasKey(token)));
    }

}
