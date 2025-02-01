package bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable;

import bg.sofia.uni.fmi.mjt.dungeons.entity.EmptySpace;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Obstacle;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Visitor;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Character;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Hero;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;

public class Spell extends Equippable {
    @Override
    public void consume(Hero hero) {

    }

    @Override
    public void accept(Visitor visitor) {

    }

    @Override
    public char toChar() {
        return 'S';
    }

    @Override
    public void visitCharacter(Character character) {

        System.out.println("Spell interacts with character:" + character.toString());
    }

    @Override
    public void visitTreasure(Treasure treasure) {

        System.out.println("Spell interacts with treasure:" + treasure.toString());
    }

    @Override
    public void visitEmptySpace(EmptySpace emptySpace) {
        System.out.println("Spell interacts with emptySpace:" + emptySpace.toString());

    }

    @Override
    public void visitObstacle(Obstacle obstacle) {
        System.out.println("Spell interacts with obstacle:" + obstacle.toString());

    }
}
