package qau.ces.business;


import java.util.List;
import qau.ces.data.ResultDAO;


public class Result {
    private String studentId;
    private String year;
    private String course;
    private String midTerm;
    private String terminal;
    private String finalGrade;

    

     // getters
    public String getStudentId() {
        return studentId;
    }

    public String getYear() {
        return year;
    }

    public String getCourse() {
        return course;
    }

    public String getMidTerm() {
        return midTerm;
    }

    public String getTerminal() {
        return terminal;
    }

    public String getFinalGrade() {
        return finalGrade;
    }

    // setters
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setMidTerm(String midTerm) {
        this.midTerm = midTerm;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public void setFinalGrade(String finalGrade) {
        this.finalGrade = finalGrade;
    }


   private static ResultDAO dao = new ResultDAO();

    public static List<String> getYears() {
        return dao.getYears();
    }

    public static List<String> getCourses() {
        return dao.getCourses();
    }

    public static List<Result> getStudents(String year, String course) {
        return dao.getStudents(year, course);
    }
}