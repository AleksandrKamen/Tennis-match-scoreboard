package validator.players;

import players.dto.CreatePlayersDto;
import players.repository.PlayersRepository;
import validator.Error;
import validator.ValidationResult;
import validator.Validator;
import java.util.ResourceBundle;

public class CreatePlayerDtoValidator implements Validator<CreatePlayersDto> {

    @Override
    public ValidationResult isValid(CreatePlayersDto object) {
        var bundle = ResourceBundle.getBundle("translations");
        var validationResult = new ValidationResult();
        if (new PlayersRepository().findByName(object.getName()).isPresent()) {
            validationResult.add(Error.of(400, bundle.getString("error.playerDto")));
        }
        return validationResult;
    }
}
