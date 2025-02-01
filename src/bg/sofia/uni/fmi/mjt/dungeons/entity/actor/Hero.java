package bg.sofia.uni.fmi.mjt.dungeons.entity.actor;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Direction;
import bg.sofia.uni.fmi.mjt.dungeons.entity.EmptySpace;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Obstacle;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Visitor;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Spell;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Weapon;

import java.util.ArrayList;

public class Hero extends Character {

    private ArrayList<Treasure> backpack;

    public Hero(String name, Stats stats, Spell spell,
                Weapon weapon) {
        super(name, stats, spell, weapon);
    }

    @Override
    public boolean move(Direction direction) {
        return false;
    }


    @Override
    public void visitCharacter(Character character) {
        System.out.println("Hero:"+ this.toString() + " interacts with another character:" + character.toString());
    }

    @Override
    public void visitTreasure(Treasure treasure) {
        System.out.println("Hero interacts with treasure");

    }

    @Override
    public void visitEmptySpace(EmptySpace emptySpace) {
        System.out.println("Hero interacts with treasure");
    }

    @Override
    public void visitObstacle(Obstacle obstacle) {
        System.out.println("Hero interacts with obstacle");
    }

    @Override
    public char toChar() {
        return 'H';
    }
}
