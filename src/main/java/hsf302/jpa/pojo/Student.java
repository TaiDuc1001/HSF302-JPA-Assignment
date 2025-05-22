package hsf302.jpa.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "Students")
public class Student {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column (name = "birthYear", length = 4, nullable = false)
    private String birthYear;

    @Column (name = "email", length = 50, nullable = false)
    private String email;

    @Column (name = "password", length = 50, nullable = false)
    private String password;

    public Student(Long id, String name, String birthYear, String email, String password) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.email = email;
        this.password = password;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
