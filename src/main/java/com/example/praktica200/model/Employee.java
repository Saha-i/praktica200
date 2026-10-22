package com.example.praktica200.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "ФИО не должно быть пустым")
    @Size(min = 2, max = 40, message = "ФИО должно содержать от 2 до 40 символов")
    @Column(name = "full_name", nullable = false, length = 40)
    private String fullName;

    @NotBlank(message = "Поле email не должно быть пустым")
    @Email(message = "Неправильный формат email")
    @Size(max = 100, message = "Email слишком длинный")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank(message = "Поле с номером телефона не должно быть пустым")
    @Pattern(regexp = "^(\\+7|8)\\d{10}$",
            message = "Телефон должен быть в формате +7XXXXXXXXXX или 8XXXXXXXXXX")
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotBlank(message = "Укажите должность сотрудника")
    @Column(name = "job_position", nullable = false)
    private String position;

    @NotNull(message = "Зарплата не должна быть пустой")
    @DecimalMin(value = "0.0", inclusive = false, message = "Зарплата должна быть больше 0")
    @Column(name = "salary", nullable = false)
    private BigDecimal salary;

    @Column(name = "active", nullable = false)
    private boolean active = true;

    public Employee() {
    }

    public Employee(String fullName, String email, String phone, String position,
                    BigDecimal salary, boolean active) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.position = position;
        this.salary = salary;
        this.active = active;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public BigDecimal getSalary() { return salary; }
    public void setSalary(BigDecimal salary) { this.salary = salary; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
