package com.example.l2cacheredis.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate(value = true)
@Table(name = "users")
public class User implements Serializable {

    private final static Long UID = 122427364827328L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "created_date")
    @CreationTimestamp
    LocalDateTime createdDate;

    @Column(name = "name")
    String name;

    @Column(name = "surname")
    String surname;

    int age;
}

