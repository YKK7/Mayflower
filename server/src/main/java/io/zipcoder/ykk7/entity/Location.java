package io.zipcoder.ykk7.entity;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
@Table(name="LOCATION")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="LOCATION_ID")
    private Long id;

    @Column(name="LOCATION_NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name="GROUP_ID", referencedColumnName = "GROUP_ID")
    private Group group;

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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }


}
