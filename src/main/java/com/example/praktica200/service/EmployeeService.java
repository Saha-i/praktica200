package com.example.praktica200.service;

import com.example.praktica200.model.Employee;
import com.example.praktica200.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public List<Employee> search(String name, Boolean active) {
        boolean hasName = name != null && !name.isBlank();
        String query = hasName ? name.trim() : null;

        if (hasName && active != null) {
            return employeeRepository.findByFullNameContainingIgnoreCaseAndActive(query, active);
        }
        if (hasName) {
            return employeeRepository.findByFullNameContainingIgnoreCase(query);
        }
        if (active != null) {
            return employeeRepository.findByActive(active);
        }
        return employeeRepository.findAll();
    }

    public Employee getById(Long id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    public boolean emailExists(String email) {
        return employeeRepository.existsByEmail(email);
    }

    public boolean emailExists(String email, Long excludeId) {
        return employeeRepository.existsByEmailAndIdNot(email, excludeId);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void delete(Long id) {
        getById(id); // 404, если не найден
        employeeRepository.deleteById(id);
    }
}
