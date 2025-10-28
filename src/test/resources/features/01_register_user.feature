Feature: Registrar usuario desde el servicio de ms-user

  Background:
    Given la API está disponible en "http://localhost:8083/api"
  @register
  @order:1
  Scenario: Registrar usuario
    When realizo una solicitud POST a "/users" con el cuerpo
    Then la respuesta debe tener código 200
    And el cuerpo debe contener el campo "username"