package edu.hitsz.aircraft;

import edu.hitsz.SupplyFactory.BloodSupplyFactory;
import edu.hitsz.SupplyFactory.BombSupplyFactory;
import edu.hitsz.SupplyFactory.FirePlusSupplyFactory;
import edu.hitsz.SupplyFactory.FireSupplyFactory;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.supply.BaseSupply;
import edu.hitsz.supply.FirePlusSupply;

import java.util.List;

/**
 * 所有种类飞机的抽象父类：
 * 敌机（BOSS, ELITE, MOB），英雄飞机
 *
 * @author hitsz
 */
public abstract class AbstractAircraft extends AbstractFlyingObject {
    /**
     * 生命值
     */
    protected int maxHp;
    protected int hp;
    private int kind;
    public BloodSupplyFactory bloodSupplyFactory = new BloodSupplyFactory();
    public FirePlusSupplyFactory firePlusSupplyFactory = new FirePlusSupplyFactory();
    public FireSupplyFactory fireSupplyFactory = new FireSupplyFactory();
    public BombSupplyFactory bombSupplyFactory = new BombSupplyFactory();
    public AbstractAircraft(int locationX, int locationY, int speedX, int speedY, int hp, int kind) {
        super(locationX, locationY, speedX, speedY);
        this.hp = hp;
        this.maxHp = hp;
        this.kind = kind;
    }

    public void decreaseHp(int decrease) {
        hp -= decrease;
        if (hp <= 0) {
            hp = 0;
            vanish();
        }
    }

    public void increaseHp(int increase) {
        hp += increase;
        if (hp >= 100) {
            hp = 100;

        }
    }

    public int getHp() {
        return hp;
    }

    public int getKind() {
        return kind;
    }

    /**
     * 飞机射击方法，可射击对象必须实现
     *
     * @return 可射击对象需实现，返回子弹
     * 非可射击对象空实现，返回null
     */
    public abstract List<BaseBullet> shoot();
    public void creatSupply(List<BaseSupply> Supplies){}
    public int increaseScore(int score){
        return score + 20;
    }

}


