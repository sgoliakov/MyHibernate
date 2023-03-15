package MyProject.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicUpdate
@DynamicInsert
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    @NotEmpty(message = "shouldn't be empty")
    @Size(min = 3, max = 30, message = "should be between 3 and 30 char")
    private String nickName;
    @NotEmpty(message = "shouldn't be empty")
    @Size(min = 3, max = 30, message = "should be between 3 and 30 char")
    private String lastName;
    @Email(message = "should be valid")
    private String mail;
    @Pattern(regexp = "\\+\\d{2}\\d{3}\\d{3}\\d{2}\\d{2}$", message = "should be valid")
    private String phone;
    private boolean isAdmin;
    @Size(min = 3, max = 30, message = "should be between 3 and 30 char")
    private String password;

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == this.getClass()) {
            Employee emp = (Employee) o;
            return this.getNickName().equals(emp.getNickName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = 31 * result + ((getNickName() != null) ? getNickName().hashCode() : 0);
        return result;
    }
}
