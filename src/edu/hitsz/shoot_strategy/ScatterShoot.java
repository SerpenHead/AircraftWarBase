package edu.hitsz.shoot_strategy;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class ScatterShoot implements ShootStrategy{
    public List<BaseBullet> shoot(HeroAircraft heroAircraft){
        List<BaseBullet> res = new LinkedList<>();
        heroAircraft.setShootNum(3);
        int direction = heroAircraft.getDirection();
        int shootNum = heroAircraft.getShootNum();
        int power = heroAircraft.getPower();
        int x = heroAircraft.getLocationX();
        int speedX = heroAircraft.getSpeedX();
        int y = heroAircraft.getLocationY();
        int speedY = heroAircraft.getSpeedY() + 3*direction;
        BaseBullet bullet;
        for (int i = 0; i < shootNum; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            x = heroAircraft.getLocationX();
            speedX = heroAircraft.getSpeedX() + (i - 1);
            bullet = new HeroBullet(x, y, speedX, speedY, power);
            res.add(bullet);
        }
        return res;
    }
    }


