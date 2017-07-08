package io.zipcoder.ykk7.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Location {

    @Id
    @GeneratedValue
    @Column(name="LOCATION_ID")
    private Long id;

    @Column(name="LOCATION_NAME")
    private String name;

    @Column(name="ADDRESS")
    private String address;

}
