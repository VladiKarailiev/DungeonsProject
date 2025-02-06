package bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Level;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Visitor;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Character;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Hero;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;

public class Weapon extends Equippable {
    public Weapon(Position pos, String name, int damage, Level lvl) {
        super(pos, name, damage, lvl);
    }

    @Override
    public void consume(Hero hero) {
        hero.equip(this);
        System.out.println("Hero tries to equip this weapon");
    }

    @Override
    public void accept(Visitor visitor) {

        visitor.visitTreasure(this);
    }

    @Override
    public char toChar() {
        return 'W';
    }

    @Override
    public void visitCharacter(Character character) {
        consume((Hero) character);
        System.out.println("Weapon interacts with character:" + character.toString());
    }

    @Override
    public void visitTreasure(Treasure treasure) {

        System.out.println("Weapon interacts with treasure:" + treasure.toString());
    }

}
