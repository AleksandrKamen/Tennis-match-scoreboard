package service;

import current_matches.CurrentMatches;
import exception.ValidationException;
import players.dto.CreatePlayersDto;
import util.StandartNameUtil;
import validator.players.PlayersNamesValidator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private static final PlayersNamesValidator playerNameValidator = new PlayersNamesValidator();
    private static ConcurrentHashMap<UUID, CurrentMatches> ongoingMatches = new ConcurrentHashMap<>();

    public CurrentMatches creatNewMatch(String player1Name, String player2Name) {

        var validationResult = playerNameValidator.isValid(List.of(player1Name,player2Name));
        if (!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrors());
        }
        var uuid = UUID.randomUUID();
        var player1 = CreatePlayersDto.builder().name(StandartNameUtil.changeNameForWrite(player1Name)).build();
        var player2 = CreatePlayersDto.builder().name(StandartNameUtil.changeNameForWrite(player2Name)).build();
        var newMatch = new CurrentMatches(uuid, player1,player2);
        ongoingMatches.put(uuid, newMatch);
        return newMatch;
    }
    public void removeMatch(UUID uuid) {
        ongoingMatches.remove(uuid);
    }
    public Optional<CurrentMatches> getMatch(UUID uuid) {
        return Optional.ofNullable(ongoingMatches.get(uuid));
    }
    public boolean containsMatch(UUID uuid) {
        return ongoingMatches.containsKey(uuid);
    }
}
