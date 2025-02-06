package bg.sofia.uni.fmi.mjt.dungeons.entity.actor;

public record Stats(Integer health, Integer mana, Integer attack, Integer defense) {
    @Override
    public String toString() {
        return "health=" + health + ", mana=" + mana + ", attack=" + attack + ", defense=" + defense;
    }
}
