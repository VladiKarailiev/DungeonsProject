package bg.sofia.uni.fmi.mjt.dungeons.entity;

import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Character;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;

public class EmptySpace extends Entity {
    @Override
    public void accept(Visitor visitor) {
        visitor.visitEmptySpace(this);
    }

    @Override
    public char toChar() {
        return '*';
    }

    @Override
    public void visitCharacter(Character character) {

        System.out.println("Empty space interacts with character" + character.toString());
    }

    @Override
    public void visitTreasure(Treasure treasure) {
        System.out.println("Empty space interacts with treasure" + treasure.toString());

    }

    @Override
    public void visitEmptySpace(EmptySpace emptySpace) {
        System.out.println("Empty space interacts with another empty space" + emptySpace.toString());

    }

    @Override
    public void visitObstacle(Obstacle obstacle) {
        System.out.println("Empty space interacts with obstacle" + obstacle.toString());

    }
}
