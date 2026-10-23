
package com.te.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(
    name = "member",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_member_email",
            columnNames = "email"
        )
    }
)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Member name cannot be empty")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Enter a valid email address")
    @Size(max = 150, message = "Email cannot exceed 150 characters")
    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(
        regexp = "^[0-9]{10}$",
        message = "Phone number must contain exactly 10 digits"
    )
    @Column(nullable = false, length = 10)
    private String phone;

    @NotBlank(message = "Address cannot be empty")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    @Column(nullable = false, length = 255)
    private String address;

    @NotNull(message = "Membership date is required")
    @PastOrPresent(message = "Membership date cannot be in the future")
    @Column(name = "membership_date", nullable = false)
    private LocalDate membershipDate;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(LocalDate membershipDate) {
        this.membershipDate = membershipDate;
    }
}
