package com.myblogrestapi.entity;

import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.NoArgsConstructor;



import javax.persistence.*;



@Data

@AllArgsConstructor

@NoArgsConstructor



@Entity

@Table(name = "comments")

public class Comment {



    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;



    private String name;

    private String email;

    private String body;




//    @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)

    private Post post;

}
