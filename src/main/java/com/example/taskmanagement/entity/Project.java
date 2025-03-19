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

    @ManyToMany
    @JoinTable(
            name = "projects_team_members",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> teamMembers = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "projects_tasks",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
    private List<Task> tasks = new ArrayList<>();

}
