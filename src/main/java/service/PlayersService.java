package service;

import lombok.RequiredArgsConstructor;
import mapper.players.CreatePlayersMapper;
import mapper.players.ReadPlayersMapper;
import repository.PlayersRepository;

@RequiredArgsConstructor
public class PlayersService {
    private final PlayersRepository playersRepository;
    private final ReadPlayersMapper readPlayersMapper;
    private final CreatePlayersMapper createPlayersMapper;



}
