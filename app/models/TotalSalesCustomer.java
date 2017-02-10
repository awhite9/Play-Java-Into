package models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class TotalSalesCustomer implements Serializable
{
    @EmbeddedId
    public MyOrderId myOrderId;

    public String companyName;
    public BigDecimal totalPrice;


}
