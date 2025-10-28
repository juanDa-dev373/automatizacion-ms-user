Feature: Iniciar sesion en el microservicio de ms-user

  Background:
    Given la API de login está disponible en "http://localhost:8083"
  @login
  @order:2
  Scenario:
    When realizo una solicitud login POST a "/login"
    Then la respuesta de login debe tener código 200
    And el cuerpo de la solicitud de login debe contener el campo "token"
