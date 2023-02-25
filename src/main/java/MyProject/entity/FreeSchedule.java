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
public class FreeSchedule implements Serializable, Comparable<FreeSchedule> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "day_id", referencedColumnName = "id")
    private WorkDays day;
    @OneToOne
    @JoinColumn(name = "shifts_id")
    private WorkingShift shift;

    @Override
    public int compareTo(FreeSchedule o) {
        return this.getDay().compareTo(o.getDay());
    }
}
