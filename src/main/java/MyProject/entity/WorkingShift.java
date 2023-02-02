package MyProject.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class WorkingShift implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    LocalTime start;
    LocalTime end;

//old code
//@OneToOne(mappedBy = "shift", fetch = FetchType.LAZY /*, cascade = CascadeType.ALL*/)
//private Schedule schedule;

}
