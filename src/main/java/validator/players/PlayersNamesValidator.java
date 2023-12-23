package validator.players;

import validator.Error;
import validator.ValidationResult;
import validator.Validator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class PlayersNamesValidator implements Validator<List<String>> {
    @Override
    public ValidationResult isValid(List<String> names) {
        var bundle = ResourceBundle.getBundle("translations", Locale.US);
        var validationResult = new ValidationResult();
        var pattern = "^[a-zA-Z][a-zA-Z ]*$";
        if (!names.get(0).matches(pattern) || !names.get(1).matches(pattern)){
            validationResult.add(Error.of(400, bundle.getString("error.names")));
        }
        if (names.get(0).contains("  ") || names.get(1).contains("  ") ){
            validationResult.add(Error.of(400, bundle.getString("error.names.double-spase")));
        }
        if (names.get(0).equals(names.get(1))){
            validationResult.add(Error.of(400, bundle.getString("error.names.duplicate")));
        }
        return validationResult;
    }
}
