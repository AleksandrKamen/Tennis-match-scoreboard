package validator.players;

import validator.Error;
import validator.ValidationResult;
import validator.Validator;

import java.util.List;

public class PlayersNamesValidator implements Validator<List<String>> {
    @Override
    public ValidationResult isValid(List<String> names) {
        ValidationResult validationResult = new ValidationResult();
        if (!names.get(0).matches("^[a-zA-Z\s]*") || !names.get(1).matches("^[a-zA-Z\s]*")){
            validationResult.add(Error.of(400,"Введено некорректное имя игрока(присутствуют недопустимые символы)"));
        }
        if (names.get(0).equals(names.get(1))){
            validationResult.add(Error.of(400,"Введено одинаковое значение имен"));
        }
        return validationResult;
    }
}
