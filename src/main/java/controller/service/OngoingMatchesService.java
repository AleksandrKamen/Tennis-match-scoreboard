package controller.service;

import controller.current_matches.CurrentMatches;
import dto.players.CreatePlayersDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OngoingMatchesService { // Сервис хранит текущие матчи и позволяет их записывать/читать
    private static final OngoingMatchesService INSTANCE = new OngoingMatchesService();
    private static Map<UUID, CurrentMatches> ongoingMatches;

    public static OngoingMatchesService getInstance() {
        return INSTANCE;
    }
    public void creatNewMatch(String player1Name, String player2Name) {
        ongoingMatches = new HashMap<>();
        UUID uuid = UUID.randomUUID();
        while (containsMatch(uuid)){
            uuid = UUID.randomUUID();
        }
        var player1 = CreatePlayersDto.builder().name(player1Name).build();
        var player2 = CreatePlayersDto.builder().name(player2Name).build();
        var newMatch = CurrentMatches.builder()
                .player1(player1)
                .player2(player2)
                .uuid(uuid)
                .build();
        ongoingMatches.put(uuid, newMatch);
    }
    public void removeMatch(UUID uuid) {
        ongoingMatches.remove(uuid);
    }
    public CurrentMatches getMatch(UUID uuid) {
        return ongoingMatches.get(uuid);
    }
    public boolean containsMatch(UUID uuid) {
        return ongoingMatches.containsKey(uuid);
    }
}
