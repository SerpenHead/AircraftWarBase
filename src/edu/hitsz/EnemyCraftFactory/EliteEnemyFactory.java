package edu.hitsz.EnemyCraftFactory;

import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class EliteEnemyFactory extends AbstractEnemyFactory {
    public EliteEnemyFactory() {
        super();
    }

    @Override /*重写方法creatEliteEnemy，创造精英敌机*/
    public EliteEnemy creatEliteEnemy() {
        return new EliteEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                10,
                60,
                3);
    }

}
