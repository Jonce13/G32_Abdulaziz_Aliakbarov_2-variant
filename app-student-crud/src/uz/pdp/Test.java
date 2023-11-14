package uz.pdp;


import uz.pdp.service.StudentServiceImpl;

public class Test {
    public static void main(String[] args) {
        StudentServiceImpl studentService=StudentServiceImpl.getInstance();
        studentService.desialize();


        studentService.serialize();

    }
}
