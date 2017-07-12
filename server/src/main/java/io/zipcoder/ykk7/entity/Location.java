package io.zipcoder.ykk7.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="LOCATION_ID")
    private Long id;

    @Column(name="LOCATION_NAME")
    private String name;

    @Column(name="ADDRESSES")
    @OneToMany(mappedBy="location")
    private List<Address> addresses;

    @ManyToOne
    @JoinColumn(name="GROUP")
    private Group group;

    @Column(name="ORDERS")
    @OneToMany(mappedBy = "location")
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
