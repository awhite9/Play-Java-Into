package models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Orders
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderID")
    public Long orderID;

    @Column(name = "customerID")
    public String customerID;

    @Column(name = "employeeID")
    public Long employeeID;

    @Column(name = "orderDate")
    public Date orderDate;

    @ManyToOne(optional=false)
    @JoinColumn(name = "employeeId")
    public Employees employee;



}