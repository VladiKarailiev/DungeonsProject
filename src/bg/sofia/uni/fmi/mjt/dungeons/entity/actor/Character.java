package bg.sofia.uni.fmi.mjt.dungeons.entity.actor;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Entity;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Visitor;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Spell;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Weapon;

public abstract class Character extends Entity implements Actor {
    private final String name;
    private Stats stats;
    private Spell spell;
    private Weapon weapon;
    //i level!

    public Character(String name, Stats stats, Spell spell, Weapon weapon) {
        this.name = name;
        this.stats = stats;
        this.spell = spell;
        this.weapon = weapon;
    }

    @Override
    public void equip(Weapon weapon) {

    }

    @Override
    public void learn(Spell spell) {

    }

    @Override
    public void takeHealing(Integer amount) {

    }

    @Override
    public void takeDamage(Integer amount) {

    }

    @Override
    public boolean isAlive() {
        return stats.health() > 0;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitCharacter(this);
    }
}
