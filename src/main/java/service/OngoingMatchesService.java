package service;

import exception.ValidationException;
import players.dto.CreatePlayersDto;
import current_matches.CurrentMatches;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import validator.players.PlayersNamesValidator;
import validator.ValidationResult;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OngoingMatchesService { // Сервис хранит текущие матчи и позволяет их записывать/читать
    private static final OngoingMatchesService INSTANCE = new OngoingMatchesService();
    private static final PlayersNamesValidator playerNameValidator = new PlayersNamesValidator();
    private static ConcurrentHashMap<UUID, CurrentMatches> ongoingMatches = new ConcurrentHashMap<>(); // потокобезопасно

    public static OngoingMatchesService getInstance() {
        return INSTANCE;
    }
    public CurrentMatches creatNewMatch(String player1Name, String player2Name) {

        ValidationResult validationResult = playerNameValidator.isValid(List.of(player1Name,player2Name));
        if (!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrors());
        }

        UUID uuid = UUID.randomUUID();
        var player1 = CreatePlayersDto.builder().name(player1Name).build();
        var player2 = CreatePlayersDto.builder().name(player2Name).build();
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
