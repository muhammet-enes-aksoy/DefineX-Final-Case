package com.example.taskmanagement.entity;

import com.example.taskmanagement.base.entity.BaseEntity;
import com.example.taskmanagement.enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PROJECTS")
public class Project extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DEPARTMENT", nullable = false)
    private String department;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private ProjectStatus status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "PROJECTS_MEMBERS",
            joinColumns = @JoinColumn(name = "PROJECT_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "id"))
    private List<User> teamMembers = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "PROJECTS_TASKS",
            joinColumns = @JoinColumn(name = "PROJECT_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "TASK_ID", referencedColumnName = "id"))
    private List<Task> tasks = new ArrayList<>();

}
