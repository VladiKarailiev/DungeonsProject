package bg.sofia.uni.fmi.mjt.dungeons.entity.actor;

import bg.sofia.uni.fmi.mjt.dungeons.entity.Position;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Visitor;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Spell;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Weapon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CharacterTest {
    private Character character;
    private Stats stats;
    private Spell spell;
    private Weapon weapon;
    private Position position;

    @BeforeEach
    public void setUp() {
        position = new Position(1, 1);
        stats = new Stats(100, 50, 20, 10);
        spell = mock(Spell.class);
        weapon = mock(Weapon.class);
        character = new Hero(position, "Hero", stats, spell, weapon) {
        };
    }

    @Test
    public void testCharacterThrowsExceptionForNullArguments() {
        assertThrows(IllegalArgumentException.class, () -> new Hero(null, "Hero", stats, spell, weapon) {
        }, "Character with invalid args should not be created.");
    }

    @Test
    public void testCharacterTakesDamage() {
        character.takeDamage(20);
        assertEquals(80, character.stats.health(), "Health should be modified as expected.");
    }

    @Test
    public void testCharacterTakesHealing() {
        character.takeHealing(10);
        assertEquals(110, character.stats.health(), "Health should be modified as expected.");
    }

    @Test
    public void testCharacterTakesMana() {
        character.takeMana(20);
        assertEquals(70, character.stats.mana(), "Mana should be modified as expected.");
    }

    @Test
    public void testCharacterDiesWhenHealthIsZero() {
        character.takeDamage(100);
        assertFalse(character.isAlive(), "IsAlive should return false.");
    }

    @Test
    public void testCharacterAcceptsVisitor() {
        Visitor visitor = mock(Visitor.class);
        character.accept(visitor);
        verify(visitor).visitCharacter(character);
    }
}
