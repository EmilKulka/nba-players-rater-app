package org.example.nbaplayersrater.mapper;

import org.example.nbaplayersrater.dto.MatchupDto;
import org.example.nbaplayersrater.model.Matchup;
import org.example.nbaplayersrater.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchupMapper {
    MatchupMapper INSTANCE = Mappers.getMapper(MatchupMapper.class);

    @Mapping(source = "matchup.id", target = "id")
    @Mapping(source = "player.id", target = "playerId")
    @Mapping(source = "player.name", target = "playerName")
    @Mapping(source = "player.surname", target = "playerSurname")
    @Mapping(source = "player.imgUrl", target = "playerImgUrl")
    @Mapping(source = "player2.id", target = "player2Id")
    @Mapping(source = "player2.name", target = "player2Name")
    @Mapping(source = "player2.surname", target = "player2Surname")
    @Mapping(source = "player2.imgUrl", target = "player2ImgUrl")
    MatchupDto toMatchupDto(Matchup matchup, Player player, Player player2);
}
