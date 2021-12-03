package com.ostapdev.todo.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "tasks")
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String taskDescription;

    @NonNull
    private Boolean done;
}