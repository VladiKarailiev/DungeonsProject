package bg.sofia.uni.fmi.mjt.dungeons.entity.actor;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Direction;
import bg.sofia.uni.fmi.mjt.dungeons.entity.EmptySpace;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Obstacle;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Spell;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Weapon;

public class Minion extends Enemy {
    public Minion(String name, Stats stats, Spell spell,
                  Weapon weapon) {
        super(name, stats, spell, weapon);
    }

    @Override
    public boolean move(Direction direction) {
        return false;
    }

    @Override
    public void visitCharacter(Character character) {

        System.out.println("Minion interacts with character" + character.toString());
    }

    @Override
    public void visitTreasure(Treasure treasure) {

        System.out.println("Minion interacts with treasure" + treasure.toString());
    }

    @Override
    public void visitEmptySpace(EmptySpace emptySpace) {

        System.out.println("Minion interacts with empty space:" + emptySpace.toString());

    }

    @Override
    public void visitObstacle(Obstacle obstacle) {
        System.out.println("Minion interacts with obstacle:" + obstacle.toString());

    }
}
