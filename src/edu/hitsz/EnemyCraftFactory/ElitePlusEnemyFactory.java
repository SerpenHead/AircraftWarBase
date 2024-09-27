package edu.hitsz.EnemyCraftFactory;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.ElitePlusEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
public class ElitePlusEnemyFactory extends AbstractEnemyFactory{
    public ElitePlusEnemyFactory(){
        super();
    }

    @Override
    public ElitePlusEnemy creatElitePlusEnemy(){
        return new ElitePlusEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                0,
                6,
                120,
                4);
    }
}
