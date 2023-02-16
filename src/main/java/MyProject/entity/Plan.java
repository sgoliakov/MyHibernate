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
public class Plan implements Serializable, Comparable<Plan> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "day_id")
    private WorkDays day;
    @OneToOne
    @JoinColumn(name = "shifts_id")
    private WorkingShift shift;

    @Override
    public int compareTo(Plan o) {
        return this.getDay().compareTo(o.getDay());
    }
}
