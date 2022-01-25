package org.pg4200.ex08;

import java.util.HashMap;
import java.util.Map;

public class Course {
    public String course_code;
    public String topics;
    public String evaluation;
    Map<Student, Integer> points;

    public Course(){
        this.course_code = "pg0";
        this.topics = "programming; design";
        this.evaluation = "exam";
        this.points = new HashMap<>();
    }
    public Course(String code, String evaluation){
        this.course_code = code;
        this.evaluation = evaluation;
        this.topics = "programming";
        this.points = new HashMap<>();
    }

    public String getEvaluation(){
        return evaluation;
    }

    public String getTopics(){
        return topics;
    }

    public Map<Student, Integer> getPoints(){
        return points;
    }
}
