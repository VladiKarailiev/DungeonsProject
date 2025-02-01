import bg.sofia.uni.fmi.mjt.dungeons.entity.EmptySpace;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Entity;
import bg.sofia.uni.fmi.mjt.dungeons.entity.Obstacle;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Hero;
import bg.sofia.uni.fmi.mjt.dungeons.entity.actor.Stats;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Spell;
import bg.sofia.uni.fmi.mjt.dungeons.entity.treasure.equippable.Weapon;
@SuppressWarnings("checkstyle:TypeName")
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

        Stats stats = new Stats(100, 50, 2, 3);
        map[1][1] = new Hero("pich", stats, null, null);
        map[2][1] = new Spell("Sabq", 5, 1);
        //map[2][1] = new HealthPotion(1);
        //map[1][2] = new ManaPotion(1);
        //map[4][4] = new Minion("neshtastnik", stats, null, null);
        map[2][1].accept(map[1][1]);
        printmap(map);
    }
}


// moga da interactvam s weapon i spellove
// moga da interactvam s potioni
//