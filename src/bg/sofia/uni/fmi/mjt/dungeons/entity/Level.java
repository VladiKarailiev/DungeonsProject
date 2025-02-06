package bg.sofia.uni.fmi.mjt.dungeons.entity;

public class Level {
    private static final int EXP_CAPACITY = 100;

    private int experience;
    private int level;

    public int getXP() {
        return level * EXP_CAPACITY + experience;
    }

    public int getLevel() {
        return level;
    }

    public Level addXP(int xp) {
        experience += xp;
        if (experience >= EXP_CAPACITY) {
            level += experience / EXP_CAPACITY;
            experience %= EXP_CAPACITY;
        }
        return this;
    }

    @Override
    public String toString() {
        return "LVL=" + level + "exp=" + experience;
    }
}
