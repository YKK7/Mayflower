package io.zipcoder.ykk7.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Order {

    @Id
    @GeneratedValue
    @Column(name="ORDER_ID")
    private Long id;

    @Column(name="TIME_IN")
    @Temporal(TemporalType.TIME)
    private Date timeIn;

    @Column(name="LOCATION_ID")
    private Location location;
}
