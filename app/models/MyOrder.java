package models;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class MyOrder implements Serializable
{
    @EmbeddedId
    public MyOrderId myOrderId;

    public String customerId;
    public Long quantity;
}
