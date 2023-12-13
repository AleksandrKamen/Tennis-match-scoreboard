package service;

import exception.ValidationException;
import players.dto.CreatePlayersDto;
import current_matches.CurrentMatches;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import validator.players.PlayersNamesValidator;
import validator.ValidationResult;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OngoingMatchesService {
    private static final OngoingMatchesService INSTANCE = new OngoingMatchesService();
    private static final PlayersNamesValidator playerNameValidator = new PlayersNamesValidator();
    private static ConcurrentHashMap<UUID, CurrentMatches> ongoingMatches = new ConcurrentHashMap<>();
    public static OngoingMatchesService getInstance() {
        return INSTANCE;
    }
    public CurrentMatches creatNewMatch(String player1Name, String player2Name) {

        var validationResult = playerNameValidator.isValid(List.of(player1Name,player2Name));
        if (!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrors());
        }
        var standardizePlayerName1 = standardizePlayerName(player1Name);
        var standardizePlayerName2 = standardizePlayerName(player2Name);

        UUID uuid = UUID.randomUUID();
        var player1 = CreatePlayersDto.builder().name(standardizePlayerName1).build();
        var player2 = CreatePlayersDto.builder().name(standardizePlayerName2).build();
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
    public String standardizePlayerName(String name){
        return Arrays.stream(name.toLowerCase().trim()
                        .split(" "))
                        .map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1))
                        .collect(Collectors.joining(" "));
    }
}
