package controllers;


import models.MyOrder;
import models.Orders;
import models.TotalSalesCustomer;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

import static play.libs.Json.toJson;

public class OrdersController extends Controller
{
    private final FormFactory formFactory;
    private final JPAApi jpaApi;

    @Inject
    public OrdersController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional(readOnly = true)
    public Result getOrders()
    {
        List<Orders> orders = (List<Orders>) jpaApi.em().createQuery("select orderID, customerID, employeeID, orderDate from Orders").getResultList();

        return ok(toJson(orders));
    }


    @Transactional(readOnly = true)
    public Result index()
    {
        List<Orders> orders = (List<Orders>) jpaApi.em().createQuery("Select o from Orders o").getResultList();

        return ok(views.html.orders.render(orders));

    }

    @Transactional(readOnly = true)
    public Result getOrderOrderdetailJoin()
    {
        List<MyOrder> myOrder = (List<MyOrder>) jpaApi.em().createNativeQuery("select od.orderId, od.productid, o.customerId, od.quantity from orders o join orderdetails od on o.orderid = od.orderid", MyOrder.class).getResultList();

        return ok(toJson(myOrder));
    }

    @Transactional(readOnly = true)
    public Result indexOfStuff()
    {
        List<MyOrder> myOrder = (List<MyOrder>) jpaApi.em().createNativeQuery("select od.orderId, od.productid, o.customerId, od.quantity from orders o join orderdetails od on o.orderid = od.orderid", MyOrder.class).getResultList();


        return ok(views.html.myOrder.render(myOrder));
    }

    @Transactional(readOnly = true)
    public Result getTotalSalesCustomer()
    {
        List<TotalSalesCustomer> totalSalesCustomer = (List<TotalSalesCustomer>) jpaApi.em().createNativeQuery("select od.orderId, od.productid, c.companyName, (od.unitPrice * od.quantity) as totalPrice from orderdetails od join orders o on o.orderid = od.orderid join customers c on o.customerID = c.customerID group by c.companyName limit 15", TotalSalesCustomer.class).getResultList();

        return ok(views.html.totalSalesCustomer.render(totalSalesCustomer));

    }
}

