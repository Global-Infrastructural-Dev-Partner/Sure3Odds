package com.gidp.sure3odds.entity.games;

import javax.persistence.*;

@Entity
@Table(name = "sure_countries")
public class Countries {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sure_countries_seq")
    @SequenceGenerator(name = "sure_countries_seq", sequenceName = "sure_countries_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    private String name;

    @Lob
    private String imageurl;

    public Countries(String name, String imageurl) {
        this.name = name;
        this.imageurl = imageurl;
    }

    public Countries() {
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

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
