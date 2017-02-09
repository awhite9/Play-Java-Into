package models;

import javax.persistence.*;

@Entity
public class Customers
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerID")
    public String customerID;

    @Column(name = "companyName")
    public String companyName;

    @Column(name = "contactName")
    public String contactName;

    @Column(name ="contactTitle")
    public String contactTitle;

}