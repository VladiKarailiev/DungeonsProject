package bg.sofia.uni.fmi.mjt.dungeons.entity.actor;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Entity;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Level;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Visitor;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Spell;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Weapon;

import java.util.ArrayList;

public abstract class Character extends Entity implements Actor {
    protected final String name;
    protected Stats stats;
    protected Spell spell;
    protected Weapon weapon;
    protected Level level;
    protected final ArrayList<Treasure> backpack = new ArrayList<>();

    public Character(Position position, String name, Stats stats, Spell spell, Weapon weapon) {
        super(position);
        if (name == null || stats == null) {
            throw new IllegalArgumentException("Can't create character with null as a position/name/stats");
        }
        this.name = name;
        this.stats = stats;
        this.spell = spell;
        this.weapon = weapon;
        level = new Level();
    }

    public boolean fightWith(Character character) {
        if (character == null) {
            return false;
        }
        while (character.isAlive() && isAlive()) {
            character.takeDamage(stats.attack());
            if (character.isAlive()) takeDamage(character.stats.attack());
        }
        return isAlive();
    }

    public int getXP() {
        return level.getXP();
    }

    public void addXP(int amount) {
        level.addXP(amount);
    }

    @Override
    public void equip(Weapon weapon) {
        if (weapon == null) {
            return;
        }
        if (weapon.getLevel().getLevel() <= level.getLevel() &&
            (this.weapon == null || weapon.getDamage() > this.weapon.getDamage())) {
            this.weapon = weapon;
            stats = new Stats(stats.health(), stats.mana(), stats.attack() + weapon.getDamage(), stats.defense());
        } else backpack.add(weapon);
    }

    @Override
    public void learn(Spell spell) {
        if (spell == null) {
            return;
        }
        if ((spell.getLevel().getXP() <= level.getXP() &&
            spell.getManaCost() <= stats.mana() &&
            (this.spell == null || spell.getDamage() > this.spell.getDamage()))) {
            stats = new Stats(stats.health(), stats.mana(), stats.attack() + spell.getDamage(), stats.defense());
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
        if (visitor == null) {
            return;
        }
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
        sb.append(System.lineSeparator()).append("Backpack: ");
        for (var item : backpack) {
            sb.append(item.toString()).append(" ");
        }
        sb.append(System.lineSeparator());
        return sb.toString().trim();
    }
}
