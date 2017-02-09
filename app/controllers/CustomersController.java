package controllers;


import models.Customers;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

import static play.libs.Json.toJson;

public class CustomersController extends Controller
{
    private final FormFactory formFactory;
    private final JPAApi jpaApi;

    @Inject
    public CustomersController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional(readOnly = true)
    public Result getCustomers()
    {
        List<Customers> customer = (List<Customers>) jpaApi.em().createQuery("select companyName, contactName from Customers").getResultList();

        return ok(toJson(customer));
    }


    @Transactional(readOnly = true)
    public Result index()
    {
        List<Customers> customer = (List<Customers>) jpaApi.em().createQuery("Select c from Customers c").getResultList();

        return ok(views.html.customers.render(customer));
    }
}
