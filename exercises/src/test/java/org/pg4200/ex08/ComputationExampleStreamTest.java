package org.pg4200.ex08;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComputationExampleStreamTest extends ComputationExampleTestTemplate{
    @Override
    protected ComputationExample getNewInstance() {
        return new ComputationalExampleStream();
    }

    public List<Student> generateStudents(int number){
        List<Student> studentList = new ArrayList<Student>();

        for (int i = 0; i < number; i++){
            Student s = new Student( i, "Name" + i );
            s.examPoints.put("pg4200", Math.random()*50);
            studentList.add(s);
        }

        for (int i = number; i < number + 5; i++){
            Student s = new Student( i, "Name" + i );
            s.examPoints.put("pg6102", Math.random()*50);
            studentList.add(s);
        }
        return studentList;
    }

    public AnotherExampleStreamList<Student> generateStudentsStream(int number){
        AnotherExampleStreamList<Student> studentList = new AnotherExampleStreamList<Student>();

        for (int i = 0; i < number; i++){
            Student s = new Student( i, "Name" + i );
            s.examPoints.put("pg4200", Math.random()*50);
            studentList.add(s);
        }

        for (int i = number; i < number + 5; i++){
            Student s = new Student( i, "Name" + i );
            s.examPoints.put("pg6102", Math.random()*50);
            studentList.add(s);
        }
        return studentList;
    }

    public AnotherExampleStreamList<Course> generateCoursesStream(int number, AnotherExampleStreamList<Student> students){
        AnotherExampleStreamList<Course> courseList = new AnotherExampleStreamList<Course>();
        for (int i = 0; i < number; i++){
            String eval = "presentation";
            if(i%3 == 0){
                eval = "project";
            } else if (i%3 == 1){
                eval = "exam";
            }
            Course c = new Course(""+ i, eval);
            for(Student student: students){
                c.points.put(student, (int) (Math.random()*50) );
            }
            courseList.add(c);
        }
        //Add the pg4200 for reference
        String eval = "project";
        Course c = new Course("pg4200", eval);
        for(Student student: students){
            c.points.put(student, (int) (Math.random()*50) );
        }
        courseList.add(c);

        return courseList;
    }

    public List<Course> generateCourses(int number, List<Student> students){
        List<Course> courseList = new ArrayList<Course>();
        for (int i = 0; i < number; i++){
            String eval = "presentation";
            if(i%3 == 0){
                eval = "project";
            } else if (i%3 == 1){
                eval = "exam";
            }
            Course c = new Course(""+ i, eval);
            for(Student student: students){
                c.points.put(student, (int) (Math.random()*50) );
            }
            courseList.add(c);
        }
        //Add the pg4200 for reference
        String eval = "project";
        Course c = new Course("pg4200", eval);
        for(Student student: students){
            c.points.put(student, (int) (Math.random()*50) );
        }
        courseList.add(c);

        return courseList;
    }

    @Test
    public void testSmallA(){

        List<Student> studentList = generateStudents(2);
        ComputationalExampleStream example = new ComputationalExampleStream();

        int relevantNumber = (int) studentList.stream()
                .filter(student -> student.examPoints.containsKey("pg4200"))
                .count();

        Double expected = studentList.stream()
                .filter( student -> student.examPoints.containsKey("pg4200"))
                .map(student -> student.examPoints.get("pg4200"))
                .reduce(0.0, Double::sum) / relevantNumber;

        Double avg = example.averageGrade(studentList);

        double epsilon = 0.000001d;

        assertTrue(avg <= 50.00);
        assertEquals(expected, avg, epsilon);
    }

    @Test
    public void testSubA(){

        List<Student> studentList = generateStudents(25);
        ComputationalExampleStream example = new ComputationalExampleStream();

        int relevantNumber = (int) studentList.stream()
                .filter(student -> student.examPoints.containsKey("pg4200"))
                .count();

        double expected = studentList.stream()
                .filter( student -> student.examPoints.containsKey("pg4200"))
                .map(student -> student.examPoints.get("pg4200"))
                .reduce(0.0, Double::sum) / relevantNumber;

        double avg = example.averageGrade(studentList);

        double epsilon = 0.000001d;

        assertTrue(avg <= 50.00);
        assertEquals(expected, avg, epsilon);
    }

    @Test
    public void testSubB(){

        List<Student> studentList = generateStudents(25);
        List<Course> courseList = generateCourses(10, studentList);

        ComputationalExampleStream example = new ComputationalExampleStream();

        List<String> names = example.topCandidates(courseList);

        assertTrue(names.size() > 0);
        assertTrue(names.stream().allMatch(n -> n.contains("Name")));

    }
}
