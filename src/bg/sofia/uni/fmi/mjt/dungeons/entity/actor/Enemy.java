package bg.sofia.uni.fmi.mjt.dungeons.entity.actor;

import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Spell;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Weapon;

public abstract class Enemy extends Character {
    public Enemy(String name, Stats stats, Spell spell,
                 Weapon weapon) {
        super(name, stats, spell, weapon);
    }
}
