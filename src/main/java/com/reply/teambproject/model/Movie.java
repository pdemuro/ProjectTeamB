package com.reply.teambproject.model;

import com.reply.teambproject.enums.Category;
import com.reply.teambproject.enums.Gender;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "movies")
public class Movie extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<Actor> actors = new ArrayList<>();

    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

}

