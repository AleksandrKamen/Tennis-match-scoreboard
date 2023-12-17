package matches.service;

import matches.dto.CreateMathesDto;
import matches.dto.ReadMatchesDto;
import matches.entity.MatchesEntity;
import matches.mapper.CreateMathesMapper;
import matches.mapper.ReadMatchesMapper;
import matches.repository.MathesRepository;
import players.repository.PlayersRepository;
import java.util.Collections;
import java.util.List;

public class MatchesService {
    private static final MathesRepository mathesRepository = new MathesRepository();
    private static final CreateMathesMapper createMathesMapper = new CreateMathesMapper();
    private static final ReadMatchesMapper readMatchesMapper = new ReadMatchesMapper();

     public CreateMathesDto createMatch(CreateMathesDto createMathesDto) {
        var matchesEntity = createMathesMapper.mapFrom(createMathesDto);
        mathesRepository.save(matchesEntity);
        return createMathesDto;
    }

     public List<ReadMatchesDto> findAllMatches() {
        return mathesRepository.findAll()
                .stream()
                .map(readMatchesMapper::mapFrom)
                .toList();
    }

    public List<ReadMatchesDto> findMatchesWithPagination(int page) {
        int offset = page * 7 - 7;
        int limit = 7;
        return mathesRepository.findMatchesWithPagination(offset, limit).stream()
                .map(readMatchesMapper::mapFrom)
                .toList();
    }

    public List<ReadMatchesDto> findMatchesByPlayerName(String playerName) {
        var matchesByPlayerName = mathesRepository.findMatchesByPlayerName(playerName);
        if (!matchesByPlayerName.isEmpty()) {
            return matchesByPlayerName.stream()
                    .map(readMatchesMapper::mapFrom)
                    .toList();
        }
        return Collections.emptyList();
    }

    public List<ReadMatchesDto> findMatchesByPlayerName(String playerName, int page) {
        int offset = page * 7 - 7;
        int limit = 7;
        var matchesByPlayerNameWithPagination = mathesRepository.findMatchesByPlayerNameWithPagination(playerName, offset, limit);
        if (!matchesByPlayerNameWithPagination.isEmpty()) {
            return mathesRepository.findMatchesByPlayerNameWithPagination(playerName, offset, limit).stream()
                    .map(readMatchesMapper::mapFrom)
                    .toList();
        }
        return Collections.emptyList();
    }
}
