package io.zipcoder.ykk7.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="location")
public class Location {

    @Id
    @GeneratedValue
    @Column(name="LOCATION_ID")
    private Long id;

    @Column(name="LOCATION_NAME")
    private String name;

    @Column(name="ADDRESS")
    private Address address;

    @Column(name="GROUP")
    private Group group;



}
