package com.example.qlsv;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Leanners {
    @SerializedName("leannerId")
    private int leannerId;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("enrollmentDate")
    private String enrollmentDate;

    @SerializedName("majorId")
    private int majorId;

    @SerializedName("interface")
    private String interfacePath; // Đổi tên để tránh trùng từ khóa "interface"

    @SerializedName("enrollments")
    private List<Object> enrollments; // Dùng Object nếu không biết chi tiết dữ liệu bên trong

    @SerializedName("major")
    private Object major; // Dùng Object nếu dữ liệu "major" là null hoặc không rõ

    // Getters và Setters
    public int getLeannerId() {
        return leannerId;
    }

    public void setLeannerId(int leannerId) {
        this.leannerId = leannerId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public int getMajorId() {
        return majorId;
    }

    public void setMajorId(int majorId) {
        this.majorId = majorId;
    }

    public String getInterfacePath() {
        return interfacePath;
    }

    public void setInterfacePath(String interfacePath) {
        this.interfacePath = interfacePath;
    }

    public List<Object> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Object> enrollments) {
        this.enrollments = enrollments;
    }

    public Object getMajor() {
        return major;
    }

    public void setMajor(Object major) {
        this.major = major;
    }
}
