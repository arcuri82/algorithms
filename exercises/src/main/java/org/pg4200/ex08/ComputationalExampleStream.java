package org.pg4200.ex08;

import org.pg4200.ex06.Author;
import org.pg4200.ex06.Book;
import org.pg4200.ex08.AnotherExampleStreamSupport;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class ComputationalExampleStream implements ComputationExample{
    @Override
    public List<String> compute(List<Book> books) {

        return books.stream()
                .filter(book -> (book.getYear() >= 2010 && book.getYear() <= 2015))
                .filter(b -> {return (b.getAuthors().size() >= 2); })
                .flatMap(book -> {return book.getAuthors().stream();} )
                .filter(author -> {return author.getName() != null && author.getSurname() != null; })
                .map(author -> {return author.getName() + " " + author.getSurname();})
                .distinct()//.count();
                .collect(Collectors.toList());

        //return null;
    }

    public double averageGradeRubric(List<Student> students){

        Double sum = students.stream()
                .filter( student -> student.examPoints.containsKey("pg4200"))
                .map(student -> student.examPoints.get("pg4200"))
                .reduce(0.0, Double::sum);


        return sum/students.size();
    }

    public List<String> topCandidatesRubric(List<Course> courseList ){

        var maybe = courseList.stream()
                .filter(course -> {return (course.evaluation.equalsIgnoreCase("project") || course.evaluation.equalsIgnoreCase("exam"));})
                .filter(course -> {return (course.topics.equalsIgnoreCase("programming"));})
                .filter(course -> {return (course.course_code.equalsIgnoreCase("pg4200"));})
                .flatMap(course -> {return (course.points).keySet().stream();})
                .sorted(Comparator.comparing(student -> student.getExamPoints().get("programming")))
                .map(Student::getName)
                .distinct()
                .collect(Collectors.toList())
                ;


        return maybe;
    }

    //--------------------

    public double averageGrade(List<Student> students){
        String course = "pg4200";

        Objects.requireNonNull(students);
        Objects.requireNonNull(course);

        return students.stream()
                .map(S -> S.examPoints.get(course)) // I assume that examPoints was meant to be public rather than private in the Student class
                .filter(P -> P != null)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElseThrow(IllegalArgumentException::new); // e.g. if the student list is empty

    }

    public List<String> topCandidates(List<Course> courses ){
        String courseCode = "pg4200";
        String topic = "programming";

        Objects.requireNonNull(courses);

        // I'm guessing the Course-class intended to have public fields instead of private. I'm also guessing that it should have been
        // Integer and not int as the second generic value in the points-map (these values can't be of primitive types).

        // I think something is missing from the task description. It suddenly says that I have to arrange "them" in decreasing order of points obtained in the "pg4200" exam. "them" can only refer
        // to the students, since the courses can't be ordered by exam points. But this is weird, since the students weren't mentioned earlier in the description. Then the question arises what
        // students we are talking about. The students that have completed en exam or project about programming in the "pg4200" course? If so, some of these students might not have taken the
        // pg4200 exam (since they might only have done a project), so ordering wouldn't make sense (how do you order something with a null value?). If I should only order the students that have
        // completed the pg4200 exam, why not just do that instead of going through all of the other filtering. I hope you see where I'm getting at.

        // This is my interpretation of the task:
        /*
        Extract all the students from all the courses that are evaluated by means of a project or an exam, and whose topics
        include “programming” and whose course code is "pg4200". Then, arrange these students in decreasing order of points they obtained in their course, and return their names.
        Each student should only appear once, even if they passed several such courses.
        */

        return courses.stream()
                .filter(C -> C.evaluation != null && C.topics != null && C.course_code != null && C.points != null)
                .filter(C -> C.evaluation.equals("project") || C.evaluation.equals("exam"))
                .filter(C -> C.topics.equals("programming")) // Since topics is plural, should each object have more than just one topic? If so, why is it a String and not a List<String> (the topics might be separated by a space, but this would be ridiculous). I'll just assume that there's only one topic per object.
                .filter(C -> C.course_code.equals("pg4200")) // These four filters could of course be combined into one big expression, but the code is better structured like this in my opinion.
                .flatMap(C -> C.points.entrySet().stream())
                .filter(C -> C.getValue() != null)
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(C -> C.getKey().name)
                .distinct()
                .collect(Collectors.toList());

        // ^ You can see that I've null-checked a lot in the code. Some people say that it's good practice, but I think it's nearly overdone here. Still, I'll leave it in here so that those people won't freak out.
    }

}
