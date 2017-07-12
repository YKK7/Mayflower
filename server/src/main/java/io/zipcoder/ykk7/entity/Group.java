package io.zipcoder.ykk7.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="GROUP_ID")
    private Long id;

    @Column(name="GROUP_NAME")
    private String name;

    @Column(name="SUBGROUP")
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<SubGroup> subGroups;


    @Column(name="LOCATIONS")
    @OneToMany(mappedBy="group")
    private List<Location> locations;

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

    public List<SubGroup> getSubGroups() {
        return subGroups;
    }

    public void setSubGroups(List<SubGroup> subGroups) {
        this.subGroups = subGroups;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
