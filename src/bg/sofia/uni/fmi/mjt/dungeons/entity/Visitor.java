package bg.sofia.uni.fmi.mjt.dungeons.entity;

import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Character;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;

public interface Visitor {

    void visitCharacter(Character character);

    void visitTreasure(Treasure treasure);

}
