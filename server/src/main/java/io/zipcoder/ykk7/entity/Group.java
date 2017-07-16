package io.zipcoder.ykk7.entity;

import com.sun.tools.javac.jvm.Gen;

import javax.persistence.*;

@Entity
@Table(name="GROUP")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="GROUP_ID")
    private Long id;

    @Column(name="GROUP_NAME")
    private String name;

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


}
