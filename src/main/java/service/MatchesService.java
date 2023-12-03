package service;

import lombok.RequiredArgsConstructor;
import mapper.matches.CreateMathesMapper;
import mapper.matches.ReadMatchesMapper;
import repository.MathesRepository;

@RequiredArgsConstructor
public class MatchesService {
    private final MathesRepository mathesRepository;
    private final ReadMatchesMapper readMatchesMapper;
    private final CreateMathesMapper createMathesMapper;
}
