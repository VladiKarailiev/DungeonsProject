package bg.sofia.uni.fmi.mjt.dungeons.entity;

import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Character;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;

public class Obstacle extends Entity {
    @Override
    public void accept(Visitor visitor) {
        visitor.visitObstacle(this);
    }

    @Override
    public char toChar() {
        return '#';
    }

    @Override
    public void visitCharacter(Character character) {
        System.out.println("Obstacle interacts with character");
    }

    @Override
    public void visitTreasure(Treasure treasure) {
        System.out.println("Obstacle interacts with treasure");

    }

    @Override
    public void visitEmptySpace(EmptySpace emptySpace) {
        System.out.println("Obstacle interacts with empty space");

    }

    @Override
    public void visitObstacle(Obstacle obstacle) {
        System.out.println("Obstacle interacts with another obstacle");
    }
}
