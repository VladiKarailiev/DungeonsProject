package bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Level;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Visitor;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Hero;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Character;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

class WeaponTest {

    private Weapon weapon;
    private Position position;
    private Level level;
    private Character character;
    private Hero hero;
    private Treasure treasure;
    private Visitor visitor;

    @BeforeEach
    public void setUp() {
        position = mock(Position.class);
        level = mock(Level.class);
        hero = mock(Hero.class);
        weapon = new Weapon(position, "Sword of Power", 100, level);
        character = mock(Character.class);
        treasure = mock(Treasure.class);
        visitor = mock(Visitor.class);
    }

    @Test
    public void testWeaponConsumeHeroEquipsWeapon() {
        weapon.consume(hero);

        verify(hero, times(1)).equip(weapon);
    }

    @Test
    public void testWeaponConsumeNullCharacter() {
        weapon.consume(null);

        verifyNoInteractions(hero);
    }

    @Test
    public void testWeaponAcceptVisitor() {
        weapon.accept(visitor);

        verify(visitor, times(1)).visitTreasure(weapon);
    }

    @Test
    public void testWeaponVisitCharacter() {
        weapon.visitCharacter(hero);

        verify(hero, times(1)).equip(weapon);
    }

    @Test
    public void testWeaponVisitCharacterNullCharacter() {
        weapon.visitCharacter(null);

        verifyNoInteractions(hero);
    }

    @Test
    public void testWeaponVisitTreasure() {
        weapon.visitTreasure(treasure);
        System.out.println("Weapon interacts with treasure:" + treasure.toString());
    }

    @Test
    public void testWeaponVisitTreasureNullTreasure() {
        weapon.visitTreasure(null);
        verifyNoInteractions(treasure);
    }

    @Test
    public void testWeaponToChar() {
        assertEquals('W', weapon.toChar());
    }

    @Test
    public void testWeaponAcceptVisitorNullVisitor() {
        weapon.accept(null);
        verifyNoInteractions(visitor);
    }
}
