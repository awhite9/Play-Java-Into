package models;

import javax.persistence.*;

@Entity
public class Employees
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeID")
    public Long employeeID;

    @Column(name = "lastName")
    public String lastName;

    @Column(name = "firstName")
    public String firstName;

    @Column(name ="photo")
    public byte[] photo;

    @ManyToOne(optional=true)
    @JoinColumn(name = "reportsTo")
    public Employees manager;

}
