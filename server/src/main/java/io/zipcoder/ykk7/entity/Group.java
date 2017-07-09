package io.zipcoder.ykk7.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="group")
public class Group {

    @Id
    @GeneratedValue
    @Column(name="GROUP_ID")
    private Long id;

    @Column(name="GROUP_NAME")
    private String name;

    @Column(name="SUBGROUP")
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<SubGroup> subGroups;

    private List<Group> neighbors;
}
