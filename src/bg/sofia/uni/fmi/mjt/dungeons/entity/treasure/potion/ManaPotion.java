package bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.potion;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Visitor;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Character;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Hero;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;

public class ManaPotion extends Potion {
    public ManaPotion(Integer amount) {
        super(amount);
    }

    @Override
    public void consume(Hero hero) {

        System.out.println("hero:" + hero.toString() + " consumed mana pot:" + this.toString());
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitTreasure(this);
    }

    @Override
    public char toChar() {
        return 'P';
    }

    @Override
    public void visitCharacter(Character character) {
        consume((Hero) character); // TVA NE TRQQ DA E TAKA
        System.out.println("Potion interacts with character:" + character.toString());
    }

    @Override
    public void visitTreasure(Treasure treasure) {

        System.out.println("Potion interacts with treasure:" + treasure.toString());
    }

}
