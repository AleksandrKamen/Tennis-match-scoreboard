package matches.service;

import lombok.Getter;
import matches.dto.CreateMathesDto;
import matches.dto.ReadMatchesDto;
import matches.mapper.CreateMathesMapper;
import matches.mapper.ReadMatchesMapper;
import matches.repository.MathesRepository;

import java.util.Collections;
import java.util.List;

public class MatchesService {
    @Getter
    private static final Integer MATCHES_LIMIT_PER_PAGE = 7;
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
        int offset = page * MATCHES_LIMIT_PER_PAGE - MATCHES_LIMIT_PER_PAGE;
        return mathesRepository.findMatchesWithPagination(offset, MATCHES_LIMIT_PER_PAGE).stream()
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
        int offset = page * MATCHES_LIMIT_PER_PAGE - MATCHES_LIMIT_PER_PAGE;
        var matchesByPlayerNameWithPagination = mathesRepository.findMatchesByPlayerNameWithPagination(playerName, offset, MATCHES_LIMIT_PER_PAGE);
        if (!matchesByPlayerNameWithPagination.isEmpty()) {
            return mathesRepository.findMatchesByPlayerNameWithPagination(playerName, offset, MATCHES_LIMIT_PER_PAGE).stream()
                    .map(readMatchesMapper::mapFrom)
                    .toList();
        }
        return Collections.emptyList();
    }
}
