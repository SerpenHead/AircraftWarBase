package edu.hitsz.EnemyCraftFactory;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
public class BossEnemyFactory extends AbstractEnemyFactory{
    public BossEnemyFactory(){ super();}
    @Override
    public BossEnemy creatBossEnemy(){
        return new BossEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.05),
                10,
                0,
                300,
                5);
    }
    }

