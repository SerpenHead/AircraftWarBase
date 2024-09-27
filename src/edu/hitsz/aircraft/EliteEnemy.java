package edu.hitsz.aircraft;

import edu.hitsz.SupplyFactory.BloodSupplyFactory;
import edu.hitsz.SupplyFactory.BombSupplyFactory;
import edu.hitsz.SupplyFactory.FirePlusSupplyFactory;
import edu.hitsz.SupplyFactory.FireSupplyFactory;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.supply.BaseSupply;

import java.util.LinkedList;
import java.util.List;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class EliteEnemy extends AbstractAircraft {
    /*
     * 精英敌机攻击方式
     */

    /**
     * 子弹一次发射数量
     **/
    private int shootNum = 1;

    /**
     * 子弹伤害
     */
    private int power = 15;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = 1;

    /*kind = 1 英雄飞机
     * kind = 3 普通敌机
     * kind = 2 精英敌机*/

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp, int kind) {
        super(locationX, locationY, speedX, speedY, hp, kind);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT) {
            vanish();
        }
    }

    @Override
    public List<BaseBullet> shoot() {
        List<BaseBullet> res = new LinkedList<>();
        int x = this.getLocationX();
        int y = this.getLocationY() + direction * 2;
        int speedX = 0;
        int speedY = this.getSpeedY() + direction * 3;
        BaseBullet bullet;
        for (int i = 0; i < shootNum; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new EnemyBullet(x + (i * 2 - shootNum + 1) * 10, y, speedX, speedY, power);
            res.add(bullet);
        }
        return res;
    }

    public void creatSupply(List<BaseSupply> Supplies) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        // 产生0-(arr.length-1)的整数值,也是数组的索引
        int index = (int) (Math.random() * arr.length);
        int kind = arr[index];
        switch (kind) {
            case 1:
                Supplies.add(super.bloodSupplyFactory.creatBloodSupply(this));
                break;
            case 2:
                if (Math.random() < 0.4) {
                    Supplies.add(super.firePlusSupplyFactory.creatFirePlusSupply(this));
                    break;
                } else Supplies.add(fireSupplyFactory.creatFireSupply(this));
                break;
            case 3:
                Supplies.add(super.bombSupplyFactory.creatBombSupply(this));
                break;
            default:
                break;
        }
    }
    public int increaseScore(int score){
        return score + 50;
    }
}
