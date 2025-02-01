import bg.sofia.uni.fmi.mjt.dungeons.entity.EmptySpace;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Entity;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Obstacle;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Hero;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Minion;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Stats;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Weapon;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.potion.HealthPotion;

public class testvanenagluposti {

    private static void printmap(Entity[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j].toChar());
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        Entity[][] map = new Entity[6][6];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i == 0 || j == 0 || i == map.length - 1 || j == map[i].length - 1) map[i][j] = new Obstacle();
                else map[i][j] = new EmptySpace();
            }
        }

        Stats stats = new Stats(1, 1, 1, 1);
        map[1][1] = new Hero("pich", stats, null, null);
        map[2][2] = new Weapon();
        map[3][2] = new HealthPotion(1);
        map[2][2] = new Weapon();
        map[4][4] = new Minion("neshtastnik", stats, null, null);
        map[1][1].accept(map[2][2]);
        Entity hero = map[1][1];
        hero.accept(map[2][2]);
        printmap(map);
    }
}
