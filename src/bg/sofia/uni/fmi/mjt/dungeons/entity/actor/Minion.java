package bg.sofia.uni.fmi.mjt.dungeons.entity.actor;

import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Spell;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Weapon;

public class Minion extends Enemy {
    public Minion(String name, Stats stats, Spell spell,
                  Weapon weapon) {
        super(name, stats, spell, weapon);
    }
}
