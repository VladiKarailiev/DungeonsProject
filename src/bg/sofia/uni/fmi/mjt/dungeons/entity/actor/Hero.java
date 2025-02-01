package bg.sofia.uni.fmi.mjt.dungeons.entity.actor;

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
    public void visitCharacter(Character character) {
        //provide options
        boolean fightResult = fightWith(character);
        //tva moje i da trqq da se iznese
        System.out.println("Hero interacts with another character and the result is: " + fightResult);
    }

    @Override
    public void visitTreasure(Treasure treasure) {
        // provide option to store
        treasure.consume(this);
        System.out.println("Hero interacts with treasure:" + treasure.toString());

    }

    @Override
    public char toChar() {
        return 'H';
    }
}
