package com.example.quiz;

import jakarta.persistence.*;

@Entity
public class QuizResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentId;
    private int score;

    public void setStudentId(String studentId) { this.studentId = studentId; }
    public void setScore(int score) { this.score = score; }
}
