package com.example.praktica200.repository;

import com.example.praktica200.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // проверка  по email
    boolean existsByEmail(String email);

    // email занят сотрудником
    boolean existsByEmailAndIdNot(String email, Long id);

    // поиск по ФИО
    List<Employee> findByFullNameContainingIgnoreCase(String name);

    // поиск  активности
    List<Employee> findByActive(boolean active);

    // поиск по ФИО и активности
    List<Employee> findByFullNameContainingIgnoreCaseAndActive(String name, boolean active);
}
