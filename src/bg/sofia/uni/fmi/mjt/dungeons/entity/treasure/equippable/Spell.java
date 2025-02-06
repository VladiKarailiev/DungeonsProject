package bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Level;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Visitor;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Character;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Hero;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;

public class Spell extends Equippable {

    private final int manaCost;

    public Spell(Position pos, String name, int damage, int manaCost, Level lvl) {
        super(pos, name, damage, lvl);
        if (manaCost < 0) {
            throw new IllegalArgumentException("Mana Cost can't be negative");
        }
        this.manaCost = manaCost;
    }

    public int getManaCost() {
        return manaCost;
    }

    @Override
    public void consume(Character character) {
        if (character == null) {
            return;
        }
        character.learn(this);
        System.out.println("Hero tries to learn this spell");
    }

    @Override
    public void accept(Visitor visitor) {
        if (visitor == null) {
            return;
        }
        visitor.visitTreasure(this);
    }

    @Override
    public char toChar() {
        return 'S';
    }

    @Override
    public void visitCharacter(Character character) {
        if (character == null) {
            return;
        }
        try {
            consume(character);
        } catch (ClassCastException e) {
            System.out.println("Spell can't interact with this character");
        }

        System.out.println("Spell interacts with character:" + character.toString());
    }

    @Override
    public void visitTreasure(Treasure treasure) {
        if (treasure == null) {
            return;
        }
        System.out.println("Spell interacts with treasure:" + treasure.toString());
    }

}
