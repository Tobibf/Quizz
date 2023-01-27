package com.isge.gsn.Quizz.models;

/*
* This model is use like bridge For Question and Game
* */

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "moments")
public class Moment {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(name = "game_time",updatable = false)
    @CreationTimestamp
    private LocalDateTime gameTime;


}
