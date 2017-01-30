package controllers

import javax.inject._
import scala.concurrent.ExecutionContext.Implicits.global
import models.Employee
import play.api.libs.json.Json
import play.api.mvc._
import repository.EmployeeRepository

@Singleton
class EmployeeController @Inject()(employeeRepository: EmployeeRepository) extends Controller {


  def addEmployee() = Action.async {
    val employee = Employee(Long.MinValue, "Dave", "hello world", "foo-bar")
    employeeRepository.insert(employee) map {_ => Ok("record inserted")}
  }

  def listEmployee = Action.async {
    employeeRepository.list.map(x => Ok(Json.toJson(x.seq.map(_.name))))
  }

}
