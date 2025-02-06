package bg.sofia.uni.fmi.mjt.dungeons.entity.actor;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Level;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Spell;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Weapon;

public class Minion extends Enemy {
    public Minion(Position pos, String name, Stats stats, Spell spell,
                  Weapon weapon, Level lvl) {
        super(pos, name, stats, spell, weapon);
        if (lvl == null) {
            throw new IllegalArgumentException("Level can't be null");
        }
        level = lvl;
    }

    @Override
    public void visitCharacter(Character character) {
        if (character == null) {
            return;
        }
        boolean fightResult = fightWith(character);
        System.out.println(
            "Minion interacts with another character and the result is: " + (fightResult ? "won" : "lost"));
    }

    @Override
    public void visitTreasure(Treasure treasure) {
        if (treasure == null) {
            return;
        }
        System.out.println("Minion interacts with treasure" + treasure.toString());
    }

    @Override
    public char toChar() {
        return 'M';
    }
}
