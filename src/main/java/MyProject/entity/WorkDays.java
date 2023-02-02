package MyProject.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicUpdate
@DynamicInsert

public class WorkDays implements Serializable, Comparable<WorkDays> {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate day;


    @Override
    public int compareTo(WorkDays o) {
        return this.getDay().compareTo(o.getDay());
    }
}
