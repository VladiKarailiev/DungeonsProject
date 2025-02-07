package bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.potion;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Visitor;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Hero;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Character;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.Treasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

class PotionTest {

    private HealthPotion healthPotion;
    private ManaPotion manaPotion;
    private Position position;
    private Character character;
    private Hero hero;
    private Treasure treasure;
    private Visitor visitor;

    @BeforeEach
    public void setUp() {
        position = mock(Position.class);
        hero = mock(Hero.class);
        character = mock(Character.class);
        treasure = mock(Treasure.class);
        visitor = mock(Visitor.class);

        healthPotion = new HealthPotion(position, 50);
        manaPotion = new ManaPotion(position, 30);
    }

    @Test
    public void testHealthPotionConsumeHeroConsumesPotion() {
        healthPotion.consume(hero);

        verify(hero, times(1)).takeHealing(50);
        System.out.println("hero:" + hero.toString() + " consumed hp pot:" + healthPotion.toString());
    }

    @Test
    public void testHealthPotionConsumeNullCharacter() {
        healthPotion.consume(null);

        verifyNoInteractions(hero);
    }

    @Test
    public void testHealthPotionVisitCharacter() {
        healthPotion.visitCharacter(hero);

        verify(hero, times(1)).takeHealing(50);
    }

    @Test
    public void testHealthPotionVisitCharacterNullCharacter() {
        healthPotion.visitCharacter(null);

        verifyNoInteractions(hero);
    }

    @Test
    public void testHealthPotionVisitTreasure() {
        healthPotion.visitTreasure(treasure);

    }

    @Test
    public void testManaPotionConsumeHeroConsumesManaPotion() {
        manaPotion.consume(hero);

        verify(hero, times(1)).takeMana(30);
    }

    @Test
    public void testManaPotionConsumeNullCharacter() {
        assertThrows(IllegalArgumentException.class, () -> {
            manaPotion.consume(null);
        }, "Character can't be null");
    }

    @Test
    public void testManaPotionVisitCharacter() {
        manaPotion.visitCharacter(hero);

        verify(hero, times(1)).takeMana(30);
    }

    @Test
    public void testManaPotionVisitCharacterNullCharacter() {
        assertThrows(IllegalArgumentException.class, () -> {
            manaPotion.visitCharacter(null);
        }, "Character can't be null");
    }

    @Test
    public void testManaPotionVisitTreasureNullTreasure() {
        assertThrows(IllegalArgumentException.class, () -> {
            manaPotion.visitTreasure(null);
        }, "Character can't be null");
    }

    @Test
    public void testHealthPotionToChar() {
        assertEquals('P', healthPotion.toChar());
    }

    @Test
    public void testManaPotionToChar() {
        assertEquals('P', manaPotion.toChar());
    }

    @Test
    public void testHealthPotionAcceptVisitor() {
        healthPotion.accept(visitor);

        verify(visitor, times(1)).visitTreasure(healthPotion);
    }

    @Test
    public void testManaPotionAcceptVisitor() {
        manaPotion.accept(visitor);
        verify(visitor, times(1)).visitTreasure(manaPotion);
    }

    @Test
    public void testHealthPotionAcceptVisitorNullVisitor() {
        healthPotion.accept(null);
        verifyNoInteractions(visitor);
    }

    @Test
    public void testManaPotionAcceptVisitorNullVisitor() {
        manaPotion.accept(null);
        verifyNoInteractions(visitor);
    }
}
