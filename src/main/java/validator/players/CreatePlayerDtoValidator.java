package validator.players;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import players.dto.CreatePlayersDto;
import players.repository.PlayersRepository;
import validator.Error;
import validator.ValidationResult;
import validator.Validator;

@AllArgsConstructor
public class CreatePlayerDtoValidator implements Validator<CreatePlayersDto> {
   EntityManager entityManager;
    @Override
    public ValidationResult isValid(CreatePlayersDto object) {
        var validationResult = new ValidationResult();
        if (new PlayersRepository(entityManager).findByName(object.getName()).isPresent()){
            validationResult.add(Error.of(400,"Игрок уже есть в базе данных"));
        }
        return validationResult;
    }
}
