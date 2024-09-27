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

import static java.lang.Math.PI;

/**
 * 普通敌机
 * 不可射击
 *
 * @author hitsz
 */
public class BossEnemy extends AbstractAircraft {
    /*
     * 精英敌机攻击方式
     */

    /**
     * 子弹一次发射数量
     **/
    private int shootNum = 15;

    /**
     * 子弹伤害
     */
    private int power = 15;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = 1;
    public BloodSupplyFactory bloodSupplyFactory = new BloodSupplyFactory();
    public FirePlusSupplyFactory firePlusSupplyFactory = new FirePlusSupplyFactory();
    public FireSupplyFactory fireSupplyFactory = new FireSupplyFactory();
    public BombSupplyFactory bombSupplyFactory = new BombSupplyFactory();
    /*kind = 1 英雄飞机
     * kind = 3 普通敌机
     * kind = 2 精英敌机*/
    public BossEnemy(int locationX, int locationY, int speedX, int speedY, int hp, int kind) {
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
        int y = this.getLocationY() + direction * 5;
        double radius = 20; // 环的半径
        double angleIncrement = 2 * Math.PI / shootNum; // 每颗子弹的角度增量

        for (int i = 0; i < this.shootNum; i++) {
            // 计算每颗子弹的角度
            double angle = i * angleIncrement;
            // 计算子弹的坐标
            int bulletX = (int) (x + radius * Math.cos(angle));
            int bulletY = (int) (y + radius * Math.sin(angle));
            // 计算子弹的速度
            int speedX = (int) (2 + direction * 5 * Math.cos(angle));
            int speedY = (int) (2 + direction * 5 * Math.sin(angle));
            // 创建子弹并添加到列表中
            BaseBullet bullet = new EnemyBullet(bulletX, bulletY, speedX, speedY, power);
            res.add(bullet);
        }

        return res;
    }
    public void creatSupply(List<BaseSupply> Supplies) {
        int[] arr = {1, 2, 3};
        int index = (int) (Math.random() * arr.length);
        int kind = arr[index];
        for(int i : arr) {
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
    }
    public int increaseScore(int score){
        return score + 500;
    }
}
