package models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Orderdetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    public Long productID;

    @Column(name = "UnitPrice")
    public BigDecimal unitPrice;

    @Column(name = "Quantity")
    public Integer quantity;
}