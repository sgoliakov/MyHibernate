package MyProject.entity;

import MyProject.entityDAO.FK.EmpDayFK;
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
public class Schedule implements Serializable {
    @EmbeddedId
    private EmpDayFK fk;
    @OneToOne
    @JoinColumn(name = "shift_id")
    private WorkingShift shift;
}
