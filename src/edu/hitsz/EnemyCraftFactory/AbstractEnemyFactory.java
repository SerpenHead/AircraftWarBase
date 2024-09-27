package edu.hitsz.EnemyCraftFactory;

import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.aircraft.BossEnemy;
import edu.hitsz.aircraft.ElitePlusEnemy;
/*定义抽象类*/
public abstract class AbstractEnemyFactory {
    public AbstractEnemyFactory() {
    }

    ;
    /*创造精英敌机*/
    public EliteEnemy creatEliteEnemy() {
        return null;
    }

    ;
    /*创造普通敌机*/
    public MobEnemy creatMobEnemy() {
        return null;
    }
    public ElitePlusEnemy creatElitePlusEnemy(){return null;}
    public BossEnemy creatBossEnemy(){return null;}
    ;

}
