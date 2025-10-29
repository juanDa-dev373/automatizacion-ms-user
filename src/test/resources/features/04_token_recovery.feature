Feature: Recuperar contraseña desde el micro ms-user
  Background:
    Given la API esta disponible en "http://localhost:8083"
  @token-recovery
  @order:4
  Scenario:
    When realiza una peticion POST a "/api/tokens-recovery"
    Then debe tener codigo de respuesta 200
    And debe contener el campo "token" para recuperacion