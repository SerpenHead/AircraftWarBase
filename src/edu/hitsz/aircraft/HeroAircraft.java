package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.shoot_strategy.NormalShoot;
import edu.hitsz.shoot_strategy.ShootContext;
import edu.hitsz.shoot_strategy.ShootStrategy;

import java.util.List;

/**
 * 英雄飞机，游戏玩家操控
 *
 * @author hitsz
 */
/*
 * 重构为单列模式
 */
public class HeroAircraft extends AbstractAircraft {
    private static HeroAircraft singleHeroAircraft;

    public void setShootStrategy(ShootStrategy strategy) {
        this.shootContext.setStrategy(strategy);
    }

    /** 攻击方式 */
    private ShootContext shootContext = new ShootContext(new NormalShoot());

    public int getShootNum() {
        return shootNum;
    }

    public void setShootNum(int shootNum) {
        this.shootNum = shootNum;
    }

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 1;

    public int getPower() {
        return power;
    }

    /**
     * 子弹伤害
     */
    private int power = 30;

    public int getDirection() {
        return direction;
    }

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = -1;

    /**
     * @param locationX 英雄机位置x坐标
     * @param locationY 英雄机位置y坐标
     * @param speedX    英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param speedY    英雄机射出的子弹的基准速度（英雄机无特定速度）
     * @param hp        初始生命值
     */
    public HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp, int kind) {
        super(locationX, locationY, speedX, speedY, hp, kind);
    }

    public static synchronized HeroAircraft getInstance() {
        if (singleHeroAircraft == null) {
            singleHeroAircraft = new HeroAircraft(Main.WINDOW_WIDTH / 2,
                    Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight(),
                    0, 0, 100, 1);
        }
        return singleHeroAircraft;
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }

    @Override
    /**
     * 通过射击产生子弹
     * 
     * @return 射击出的子弹List
     */
    public List<BaseBullet> shoot() {
        return shootContext.executeStrategy(singleHeroAircraft);
    }
}
