package uz.pdp.service;

import uz.pdp.entity.Adress;
import uz.pdp.entity.Group;
import uz.pdp.entity.Student;
import uz.pdp.payload.StudentDTO;

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class StudentServiceImpl implements StudentService{
    private static StudentServiceImpl instance;
    private static Lock lock = new ReentrantLock();
    public List<Student> students = Collections.synchronizedList(new ArrayList<>());

    private StudentServiceImpl() {}

    public static StudentServiceImpl getInstance() {
        if (Objects.isNull(instance)) {
            lock.lock();
            if (Objects.isNull(instance))
                instance = new StudentServiceImpl();
            lock.unlock();
        }
        return instance;
    }

    @Override
    public List<StudentDTO> getByGroupId(Integer groupId) {
        return students.stream()
                .filter(student -> student.getGroup().getId().equals(groupId))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getById(Integer id) {
        return toDTO(findById(id));
    }

    @Override
    public StudentDTO add(StudentDTO studentDTO) {
        int id=students.size()+1;


        GroupServiceImpl groupService=GroupServiceImpl.getInstance();
        Optional<Group> first = groupService.groups.stream()
                .filter(group -> group.getId().equals(studentDTO.getGroupId()))
                .findFirst();
        Group group = first.orElseThrow(RuntimeException::new);

        AdressServiceImpl adressService=AdressServiceImpl.getInstance();
        Optional<Adress> optionalAdress = adressService.adresses.stream()
                .filter(adress -> adress.getId().equals(studentDTO.getAdressId()))
                .findFirst();
        Adress adress = optionalAdress.orElseThrow(RuntimeException::new);


        Student student=new Student(id,studentDTO.getFirstName(),studentDTO.getLastName(),studentDTO.getPhoneNumber()
        ,studentDTO.getBirthDate(),group,adress,studentDTO.getBiographyFilePath());
        students.add(student);

        studentDTO.setId(id);

        return studentDTO;
    }

    @Override
    public StudentDTO myEdit(Integer id, StudentDTO studentDTO) {
        Student student = findById(id);
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setBiographyFilePath(studentDTO.getBiographyFilePath());
        student.setBirthDate(studentDTO.getBirthDate());
        student.setPhoneNumber(studentDTO.getPhoneNumber());


        GroupServiceImpl groupService=GroupServiceImpl.getInstance();
        Optional<Group> optionalStudent1 = groupService.groups.stream()
                .filter(group -> group.getId().equals(studentDTO.getGroupId()))
                .findFirst();
        Group group = optionalStudent1.orElseThrow(() -> new RuntimeException("Group is not found with id:" + studentDTO.getGroupId()));
        student.setGroup(group);


        AdressServiceImpl adressService=AdressServiceImpl.getInstance();
        Optional<Adress> optionalAdress = adressService.adresses.stream()
                .filter(adress -> adress.getId().equals(studentDTO.getAdressId()))
                .findFirst();
        Adress adress = optionalAdress.orElseThrow(() -> new RuntimeException("Group is not found with id:" + studentDTO.getGroupId()));
        student.setAdress(adress);

        return toDTO(student);
    }

    @Override
    public boolean myRemove(Integer id) {
        students.remove(findById(id));
        return true;
    }

    @Override
    public String myRead(Integer id) {
        Student student = findById(id);
        File file=new File(student.getBiographyFilePath());
        String str;
        try(InputStream inputStream=new FileInputStream(file);) {
            byte[] bytes = inputStream.readAllBytes();
            str=new String(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return str;
    }

    @Override
    public boolean serialize() {
        try (ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream("db/salom.txt"))){
            objectOutputStream.writeObject(students);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean desialize() {
        try (ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream("db/salom.txt"))){
            students= (List<Student>) objectInputStream.readObject();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    public Student findById(Integer id) {
        Optional<Student> optionalStudent = students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();
        return optionalStudent.orElseThrow(()->new RuntimeException("Student is not found with id: "+id));
    }
    public StudentDTO toDTO(Student student){
        return new StudentDTO(student.getFirstName(),student.getLastName(),
                student.getPhoneNumber(),student.getBirthDate(), student.getGroup().getId(),student.getAdress().getId(),
                student.getBiographyFilePath());
    }
}
