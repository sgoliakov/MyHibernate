package MyProject.entity;

import MyProject.entityHelper.FK.EmpDayFK;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicUpdate
@DynamicInsert
public class Schedule {
    @EmbeddedId
    private EmpDayFK fk;
    @OneToOne
    @JoinColumn(name = "shift_id")
    private WorkingShift shift;
}
