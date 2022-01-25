package org.pg4200.ex08;

import java.util.HashMap;
import java.util.Map;

public class Student {
    public int student_id;
    public String name;
    Map<String, Double> examPoints;

    public Student(){
        this.student_id = 0;
        this.name = "Skippy";
        this.examPoints = new HashMap<>();
    }
    public Student(int id, String name){
        this.student_id = id;
        this.name = name;
        this.examPoints = new HashMap<>();
    }

    public Map<String, Double> getExamPoints(){
        return examPoints;
    }

    public Double getExamResult(String course){
        Double res = examPoints.get(course);
        if(res!=null){
            return res;
        }
        else return 0.0;
    }

    public String getName(){
        return name;
    }
}
