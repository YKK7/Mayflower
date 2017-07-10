package io.zipcoder.ykk7.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="subgroup")
public class SubGroup {

    @Id
    @GeneratedValue
    @Column(name="SUBGROUP_ID")
    private Long id;

    @Column(name="SUBGROUP_NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name="GROUP_ID")
    private Group group;
}
