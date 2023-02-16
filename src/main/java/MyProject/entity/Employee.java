package MyProject.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
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
    private String firstName;
    private String lastName;
    private String mail;
    private String phone;
    private boolean isAdmin;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == this.getClass()) {
            Employee emp = (Employee) o;
            return this.getFirstName().equals(emp.getFirstName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 31;
        result = 31 * result + ((getFirstName() != null) ? getFirstName().hashCode() : 0);
        return result;
    }
}
