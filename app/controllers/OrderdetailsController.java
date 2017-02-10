package controllers;

import models.Orderdetails;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

import static play.libs.Json.toJson;

public class OrderdetailsController extends Controller
{
    private final FormFactory formFactory;
    private final JPAApi jpaApi;

    @Inject
    public OrderdetailsController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional(readOnly = true)
    public Result getOrderdetails()
    {
        List<Orderdetails> orderdetails = (List<Orderdetails>) jpaApi.em().createQuery("select unitPrice, quantity from Orderdetails").getResultList();

        return ok(toJson(orderdetails));
    }


    @Transactional(readOnly = true)
    public Result index()
    {
        List<Orderdetails> orderdetails = (List<Orderdetails>) jpaApi.em().createQuery("Select od from Orderdetails od").getResultList();

        return ok(views.html.orderdetails.render(orderdetails));

    }
    @Transactional(readOnly = true)
    public Result googleCharts()
    {
        List<Orderdetails> orderdetails = (List<Orderdetails>) jpaApi.em().createQuery("Select od from Orderdetails od").getResultList();

        return ok(views.html.googlecharts.render(orderdetails));
    }
}
