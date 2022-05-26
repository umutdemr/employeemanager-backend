package tech.getarrays.employeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.employeemanager.exception.UserNotFoundException;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.repo.EmployeeRepo;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    //ÇALIŞAN EKLEME
    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());//rastgele kulanıcı kimliği
        return employeeRepo.save(employee);
    }

    //ÇALIŞAN LİSTELEME
    public List<Employee> findAllEmployees(){
        return employeeRepo.findAll();
    }


    //ÇALIŞAN GÜNCELEMEE
    public Employee updateEmployee(Employee employee){
        return employeeRepo.save(employee);
    }

    //ÇALIŞANLARI ARAMA
    public Employee findEmployeeById(Long id){
        return employeeRepo.findEmployeeById(id).orElseThrow(() -> new UserNotFoundException("User by id"+id+"was not found"));
    }
    //ÇALIŞANLARI SİLME
    @Transactional
    public Employee deleteEmployee(Long id){
        employeeRepo.deleteEmployeeById(id);
        return null;
    }

}
