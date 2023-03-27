package MyProject.util.validators;

import MyProject.entity.Employee;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MyValidator {
    private static final Validator VALIDATOR =
            Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .getValidator();

    public static List<String> validate(Employee employee) {
        List<String> errors = new ArrayList<>();
        Set<ConstraintViolation<Employee>> validate = VALIDATOR.validate(employee);
        if (!validate.isEmpty()) {
            for (ConstraintViolation<Employee> cv : validate) {
                String err = cv.getPropertyPath() + " " +
                        cv.getMessage();
                errors.add(err);
            }
        }
        return errors;
    }
}
