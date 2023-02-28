package MyProject.entity.wrapperEntity;

import MyProject.entity.WorkDays;
import MyProject.entity.WorkingShift;
import lombok.ToString;

import java.util.Objects;

@ToString
public final class WrapperSchedule {
    private final WorkingShift shift;
    private final WorkDays day;

    public WrapperSchedule(WorkingShift shift, WorkDays day) {
        this.shift = shift;
        this.day = day;
    }

    public WorkingShift shift() {
        return shift;
    }

    public WorkDays day() {
        return day;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (WrapperSchedule) obj;
        return Objects.equals(this.shift, that.shift) &&
                Objects.equals(this.day, that.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shift, day);
    }

}