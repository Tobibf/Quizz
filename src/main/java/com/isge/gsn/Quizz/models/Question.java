package com.isge.gsn.Quizz.models;

/*
* This model is use for Questions which will be used for Game
* */

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue
    private long id;

    private String content;
    private String answer;

    @OneToMany(mappedBy = "question")
   // @JsonIgnore
    private List<Answer> answers;

    @OneToMany(mappedBy = "question")
    Set<Moment> momentSet;


    @Column(name = "created_at",updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
