package bg.sofia.uni.fmi.mjt.dungeons.entity.actor;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Spell;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Weapon;

public abstract class Enemy extends Character {
    public Enemy(Position pos, String name, Stats stats, Spell spell,
                 Weapon weapon) {
        super(pos, name, stats, spell, weapon);
    }
}
