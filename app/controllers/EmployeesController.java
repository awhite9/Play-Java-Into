package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Employees;
import models.RandomUser;
import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static play.libs.Json.toJson;

public class EmployeesController extends Controller
{
    private final FormFactory formFactory;
    private final JPAApi jpaApi;

    @Inject
    public EmployeesController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional(readOnly = true)
    public Result getEmployees()
    {
        List<Employees> employees = (List<Employees>) jpaApi.em().createQuery("select employeeID, lastName, firstName from Employees").getResultList();

        return ok(toJson(employees));
    }


    @Transactional(readOnly = true)
    public Result index()
    {
        List<Employees> employees = (List<Employees>) jpaApi.em().createQuery("Select e from Employees e").getResultList();

        return ok(views.html.employees.render(employees));

    }
    @Transactional(readOnly = true)
    public Result getPicture(Long id)
    {
        Employees employees = (Employees)jpaApi.em().createQuery("select e from Employees e where employeeID = :id").setParameter("id", id).getSingleResult();

        if(employees.photo == null)
        {
            return null;
        }
        else
        {
            return ok(employees.photo).as("image/bmp");

        }
    }
    @Transactional(readOnly = true)
    public Result editEmployee(Long employeeID)
    {
        Employees employee = (Employees) jpaApi.em().createQuery("select e from Employees e where employeeID = :id").setParameter("id", employeeID).getSingleResult();
        return ok(views.html.updateEmployee.render(employee));
    }

    @Transactional
    public Result updateEmployee()
    {
        DynamicForm postedForm = formFactory.form().bindFromRequest();
        Long employeeID = new Long(postedForm.get("employeeID"));
        String lastName = postedForm.get("lastName");
        String firstName = postedForm.get("firstName");
        Employees employee = (Employees) jpaApi.em().createQuery("select e from Employees e where employeeID = :id").setParameter("id", employeeID).getSingleResult();
        employee.lastName = lastName;
        employee.firstName = firstName;
        jpaApi.em().persist(employee);
        return redirect(routes.EmployeesController.index());
    }
    @Transactional(readOnly = true)
    public Result newEmployee()
    {
        RandomUser randomUser = null;
        try
        {
            String myURL = "https://randomuser.me/api/";
            URL url = new URL(myURL);

            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            ObjectMapper objectMapper = new ObjectMapper();
            randomUser = objectMapper.readerFor(RandomUser.class).readValue(url);
        } catch (Exception e)
        {
            Logger.error("oh no! got some exception: " + e.getMessage());
        }
        return ok(views.html.newEmployee.render(randomUser));
    }


    @Transactional
    public Result addEmployee()
    {
        Employees employee = formFactory.form(Employees.class).bindFromRequest().get();
        jpaApi.em().persist(employee);
        return redirect(routes.EmployeesController.index());
    }

}
