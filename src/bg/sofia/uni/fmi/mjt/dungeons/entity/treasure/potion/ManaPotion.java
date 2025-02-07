package bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.potion;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Visitor;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Character;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;

public class ManaPotion extends Potion {
    public ManaPotion(Position pos, Integer amount) {
        super(pos, amount);
    }

    @Override
    public void consume(Character character) {
        if (character == null) {
            throw new IllegalArgumentException("Character can't be null");
        }
        character.takeMana(amount);
        System.out.println("hero:" + character.toString() + " consumed mana pot:" + this.toString());
    }

    @Override
    public void accept(Visitor visitor) {
        if (visitor != null) {
            visitor.visitTreasure(this);
        }
    }

    @Override
    public char toChar() {
        return 'P';
    }

    @Override
    public void visitCharacter(Character character) {
        if (character == null) {
            throw new IllegalArgumentException("Character can't be null");
        }
        consume(character);
        System.out.println("Potion interacts with character:" + character.toString());
    }

    @Override
    public void visitTreasure(Treasure treasure) {
        if (treasure == null) {
            throw new IllegalArgumentException("treasure can't be null");
        }
        System.out.println("Potion interacts with treasure:" + treasure.toString());
    }

}
