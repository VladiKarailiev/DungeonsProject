package bg.sofia.uni.fmi.mjt.dungeons.entity.actor;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Entity;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Level;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Visitor;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Equippable;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Spell;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Weapon;

import java.util.ArrayList;

public abstract class Character extends Entity implements Actor {
    private final String name;
    private Stats stats;
    private Spell spell;
    private Weapon weapon;
    private Level level;
    private ArrayList<Treasure> backpack = new ArrayList<>();


    public Character(Position position, String name, Stats stats, Spell spell, Weapon weapon) {
        super(position);
        this.name = name;
        this.stats = stats;
        this.spell = spell;
        this.weapon = weapon;
        level = new Level();
    }

    public boolean fightWith(Character character) {
        while (character.isAlive() && isAlive()) {
            character.takeDamage(stats.attack());
            if (character.isAlive()) takeDamage(character.stats.attack());
        }
        level.addXP(character.getXP());
        return isAlive();
    }

    public int getXP() {
        return level.getXP();
    }

    @Override
    public void equip(Weapon weapon) {
        if (weapon.getLevel().getXP() > level.getXP() &&
            (this.weapon == null || weapon.getDamage() > this.weapon.getDamage())) {
            this.weapon = weapon;
        } else backpack.add(weapon);
    }

    @Override
    public void learn(Spell spell) {
        if ((spell.getLevel().getXP() > level.getXP() &&
            spell.getManaCost() <= stats.mana() &&
            (this.spell == null || spell.getDamage() > this.spell.getDamage()))) {
            this.spell = spell;
        } else backpack.add(spell);
    }

    @Override
    public void takeHealing(Integer amount) {
        stats = new Stats(stats.health() + amount, stats.mana(), stats.attack(), stats.defense());
    }

    @Override
    public void takeMana(Integer amount) {

        stats = new Stats(stats.health(), stats.mana() + amount, stats.attack(), stats.defense());
    }

    @Override
    public void takeDamage(Integer amount) {

        stats = new Stats(stats.health() - amount, stats.mana(), stats.attack(), stats.defense());
    }

    @Override
    public boolean isAlive() {
        return stats.health() > 0;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitCharacter(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(": ");

        if (level != null) {
            sb.append(level.toString()).append(" ");
        }
        if (stats != null) {
            sb.append("<").append(stats.toString()).append("> ");
        }
        if (weapon != null) {
            sb.append(", ").append(weapon.toString());
        }
        if (spell != null) {
            sb.append(" ").append(spell.toString());
        }
        return sb.toString().trim();
    }
}
