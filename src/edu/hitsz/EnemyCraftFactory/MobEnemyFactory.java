package edu.hitsz.EnemyCraftFactory;

import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class MobEnemyFactory extends AbstractEnemyFactory {
    public MobEnemyFactory() {
        super();
    }

    @Override /*重写方法creatMobEnemy，创造普通敌机*/
    public MobEnemy creatMobEnemy() {
        return new MobEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                6,
                30,
                2);
    }


}
