package bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.potion;

import bg.sofia.uni.fmi.mjt.dungeons.entity.EmptySpace;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Obstacle;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Visitor;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Character;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Hero;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;

public class Potion implements Treasure {
    private final Integer amount;

    public Potion(Integer amount) {
        this.amount = amount;
    }

    @Override
    public void consume(Hero hero) {

    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitTreasure(this);
    }

    @Override
    public void visitCharacter(Character character) {

        System.out.println("Potion interacts with character:" + character.toString());
    }

    @Override
    public void visitTreasure(Treasure treasure) {

        System.out.println("Potion interacts with treasure:" + treasure.toString());
    }

    @Override
    public void visitEmptySpace(EmptySpace emptySpace) {

        System.out.println("Potion interacts with emptySpace:" + emptySpace.toString());
    }

    @Override
    public void visitObstacle(Obstacle obstacle) {
        System.out.println("Potion interacts with obstacle:" + obstacle.toString());

    }
}
