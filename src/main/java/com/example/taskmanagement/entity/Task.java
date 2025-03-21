package com.example.taskmanagement.entity;

import com.example.taskmanagement.base.entity.BaseEntity;
import com.example.taskmanagement.enums.TaskPriority;
import com.example.taskmanagement.enums.TaskState;
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
@Table(name = "TASKS")
public class Task extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRIORITY", nullable = false)
    private TaskPriority priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATE", nullable = false)
    private TaskState state;

    @Column(name = "REASON")
    private String reason;

    @ManyToOne
    private Project project;

    @ManyToOne
    private User assignee;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "TASKS_COMMENTS",
            joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "id"))
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "TASKS_ATTACHMENTS",
            joinColumns = @JoinColumn(name = "TASK_ID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ATTACHMENT_ID", referencedColumnName = "id"))
    private List<Attachment> attachments = new ArrayList<>();
}
