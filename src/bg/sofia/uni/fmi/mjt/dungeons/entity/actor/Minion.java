package bg.sofia.uni.fmi.mjt.dungeons.entity.actor;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Spell;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Weapon;

public class Minion extends Enemy {
    public Minion(Position pos, String name, Stats stats, Spell spell,
                  Weapon weapon) {
        super(pos, name, stats, spell, weapon);
    }

    @Override
    public void visitCharacter(Character character) {

        boolean fightResult = fightWith(character);
        //tva moje i da trqq da se iznese
        System.out.println("Minion interacts with another character and the result is: " + fightResult);
    }

    @Override
    public void visitTreasure(Treasure treasure) {

        System.out.println("Minion interacts with treasure" + treasure.toString());
    }

    @Override
    public char toChar() {
        return 'M';
    }
}
