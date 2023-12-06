package service;

import players.dto.CreatePlayersDto;
import current_matches.CurrentMatches;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OngoingMatchesService { // Сервис хранит текущие матчи и позволяет их записывать/читать
    private static final OngoingMatchesService INSTANCE = new OngoingMatchesService();
    private static ConcurrentHashMap<UUID, CurrentMatches> ongoingMatches = new ConcurrentHashMap<>(); // потокобезопасно

    public static OngoingMatchesService getInstance() {
        return INSTANCE;
    }
    public void creatNewMatch(String player1Name, String player2Name) {
        // TODO: 05.12.2023 Валидация на имена
        UUID uuid = UUID.randomUUID();
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
    public Optional<CurrentMatches> getMatch(UUID uuid) {
        return Optional.ofNullable(ongoingMatches.get(uuid));
    }
    public boolean containsMatch(UUID uuid) {
        return ongoingMatches.containsKey(uuid);
    }
}
