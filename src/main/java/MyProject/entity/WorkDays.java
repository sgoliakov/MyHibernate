package MyProject.entity;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicUpdate
@DynamicInsert
public class WorkDays implements Serializable, Comparable<WorkDays> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate day;
    @OneToMany(mappedBy = "day")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<FreeSchedule> freeSchedules;
    @OneToMany(mappedBy = "day")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<Plan> planList;

    @Override
    public int compareTo(WorkDays o) {
        return this.getDay().compareTo(o.getDay());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "day = " + day + ")";
    }
}
