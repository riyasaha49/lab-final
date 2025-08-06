package com.example.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class QuizController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuizResultRepository quizResultRepository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/start-quiz")
    public String startQuiz(@RequestParam String studentId, Model model) {
        if (studentRepository.existsByStudentId(studentId)) {
            model.addAttribute("studentId", studentId);
            return "quiz";
        } else {
            model.addAttribute("error", "অবৈধ ছাত্র আইডি");
            return "home";
        }
    }

    @PostMapping("/submit-quiz")
    public String submitQuiz(@RequestParam String studentId,
                             @RequestParam String q1,
                             @RequestParam String q2,
                             Model model) {
        int score = 0;
        if (q1.equals("option2")) score++;
        if (q2.equals("option3")) score++;

        QuizResult result = new QuizResult();
        result.setStudentId(studentId);
        result.setScore(score);
        quizResultRepository.save(result);

        model.addAttribute("score", score);
        return "result";
    }
}
