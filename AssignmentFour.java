import java.sql.*;
import java.util.Scanner;


public class AssignmentFour {
    //It's my username and password and link to my database
    private final String url = "jdbc:postgresql://localhost:5432/Assignment 4";
    private final String user = "postgres";
    private final String password = "zrb001124";

    //out print the student table
    public void getAllStudents() {
        String sql = "SELECT * FROM students";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // if has student_id, first_name, last_name, email, enrollment_date
                int id = rs.getInt("student_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                Date enrollmentDate = rs.getDate("enrollment_date");

                System.out.println(id + ", " + firstName + ", " + lastName + ", " + email + ", " + enrollmentDate);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //addStudent add a new student to the database, only if the email is unique and it is an email
    //if it is not email, it will stop processing and return
    //we need first_name, last_name, email, enrollment_date to create a student
    public void addStudent(String first_name,String last_name,String email,String enrollment_date) {
        String SQL = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            if(email.contains(".com")==false||email.contains("@")==false){
                System.out.println("Not real email!");
                return;
            }
            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setString(3, email);
            pstmt.setDate(4, Date.valueOf(enrollment_date));
            pstmt.executeUpdate();
            System.out.println("User added successfully!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //modifyStudentEmail modify student's email to the database, only if the email is unique and it is an email
    //if it is not email, it will stop processing and return
    //we need id and new email to update a student
    public void modifyStudentEmail(Integer id, String newEmail) {
        String SQL = "UPDATE students SET email=? WHERE student_id=?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            if(newEmail.contains(".com")==false||newEmail.contains("@")==false){
                System.out.println("Not real email!");
                return;
            }
            pstmt.setString(1, newEmail);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("User email updated!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //deleteStudent delete a student to the database
    //we need id to delete a student
    public void deleteStudent(Integer student_id) {
        String SQL = "DELETE FROM students WHERE student_id=?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setInt(1, student_id);
            pstmt.executeUpdate();
            System.out.println("Student deleted!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //main to pricess
    public static void main(String[] args) {

        AssignmentFour dbOps = new AssignmentFour();
        Scanner scanner = new Scanner(System.in);

        // Add a student
        //get a variable each time and check
        System.out.println("Would you like to add a student? (yes/no)");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("Write down the the first name.");
            try{
                String fname=scanner.nextLine();
                System.out.println("Write down the the last name.");
                try{
                    String lname=scanner.nextLine();
                    System.out.println("Write down the the email.");
                    try{
                        String email=scanner.nextLine();
                        System.out.println("Write down the the date.");
                        try{
                            String date=scanner.nextLine();
                            dbOps.addStudent(fname, lname,email,date);
                        }catch (NumberFormatException e){
                            System.out.println("date goes wrong");
                        }
                    }catch (NumberFormatException e){
                        System.out.println("email goes wrong");
                    }
                }catch (NumberFormatException e){
                    System.out.println("last name goes wrong");
                }
            }catch (NumberFormatException e){
                System.out.println("first name goes wrong");
            }

        }

        // Modify student's email
        //get a variable each time and check
        System.out.println("Would you like to modify a student's email? (yes/no)");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("Write down the the specified student_id.");
            try{
                int id=Integer.parseInt(scanner.nextLine());
                System.out.println("Write down the the new email.");
                try{
                    String email=scanner.nextLine();
                    dbOps.modifyStudentEmail(id, email);
                }catch (NumberFormatException e){
                    System.out.println("email goes wrong");
                }
            }catch (NumberFormatException e){
                System.out.println("id goes wrong");
            }

        }

        // Delete student
        //get a variable each time and check
        System.out.println("Would you like to delete a student? (yes/no)");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.println("Write down the the specified student_id.");
            try{
                int id=Integer.parseInt(scanner.nextLine());
                dbOps.deleteStudent(id);
            }catch (NumberFormatException e){
                System.out.println("Something goes wrong");
            }

        }
        dbOps.getAllStudents();
        scanner.close();
    }
}

