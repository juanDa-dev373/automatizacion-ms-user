Feature: Actualizar usuario con el metodo put del microservicio de ms-user

  Background:
    Given la API para el metodo put esta disponible en "http://localhost:8083"
  @update-put
  @order:3
  Scenario:
    When realizo una solicitud PUT a "/api/users/{id}"
    Then la respuesta del update debe ser 200
    And debe contener el campo "username"