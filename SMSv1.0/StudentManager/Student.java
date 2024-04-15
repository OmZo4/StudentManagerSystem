public class Student {
    String name;
    String degree;
    int age;
    Long studentID;

    Student(String name, String degree, int age, Long studentID){ //i THink this is a constructor

        this.age = age;
        this.degree = degree;
        this.name = name;
        this.studentID = studentID;

    }

    void showinfo(){
        System.out.println("Full name: " + this.name);
        System.out.println("Age: "+ this.age);
        System.out.println("Degree: " + this.degree);
        System.out.println("Student ID: " + this.studentID);
    }
}
