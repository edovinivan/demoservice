package ru.otr.demoservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Table(name = "MESSAGE")
@Entity
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "TEXT", columnDefinition = "TEXT")
    String text;

    @ManyToOne
    @JoinColumn(name = "USERS")
    Users users;

    @Column(name = "DATEWRITE")
    LocalDateTime datewrite;

    @Column(name = "TYPEMESSAGE")
    @Enumerated(EnumType.STRING)
    TypeMessage typeMessage;

    @ManyToOne
    @JoinColumn(name = "QUERY")
    Message query;
}
