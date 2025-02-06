package bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.potion;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Visitor;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Character;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Hero;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;

public class HealthPotion extends Potion {
    public HealthPotion(Position pos, Integer amount) {
        super(pos, amount);
    }

    @Override
    public void consume(Character character) {
        if (character == null) {
            return;
        }
        character.takeHealing(amount);
        System.out.println("hero:" + character.toString() + " consumed hp pot:" + this.toString());
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
        return 'P';
    }

    @Override
    public void visitCharacter(Character character) {
        if (character == null) {
            return;
        }
        consume(character);
        System.out.println("Potion interacts with character:" + character.toString());
    }

    @Override
    public void visitTreasure(Treasure treasure) {
        if (treasure == null) {
            return;
        }
        System.out.println("Potion interacts with treasure:" + treasure.toString());
    }


}
