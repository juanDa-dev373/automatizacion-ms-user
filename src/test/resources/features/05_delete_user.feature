Feature: Eliminar usuario desde el microservicio ms-user

  Background:
    Given la API para eliminar usuario esta disponible en "http://localhost:8083"
  @delete
  @order:5
  Scenario:
    When se realiza solicitud DELETE a "/api/users/{id}"
    Then debe devolver codigo de respuesta 200