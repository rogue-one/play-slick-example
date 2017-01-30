package repository

import com.google.inject.Inject
import models.Employee
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import repository.EmployeeRepository.EmployeeRepoHelper
import slick.driver.JdbcProfile

/**
  * Created by chlr on 1/30/17.
  */
class EmployeeRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)
  extends EmployeeRepoHelper
    with HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  def insert(employee: Employee) = db.run {
    employeeTableQuery += employee
  }

  def list = db.run {
    employeeTableQuery.result
  }

}

object EmployeeRepository {

  trait EmployeeRepoHelper {

    self: HasDatabaseConfigProvider[JdbcProfile] =>

    import driver.api._

    lazy protected val employeeTableQuery = TableQuery[EmployeesTableDef]

    class EmployeesTableDef(tag: Tag) extends Table[Employee](tag, "employee_data") {

      def id = column[Long]("id", O.PrimaryKey, O.AutoInc)

      def name = column[String]("name")

      def resume = column[String]("resume")

      def additionalInformation = column[String]("additionalInformation")

      override def * =
        (id, name, resume, additionalInformation) <>(Employee.tupled, Employee.unapply)
    }

  }

}