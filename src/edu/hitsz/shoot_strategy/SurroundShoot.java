package edu.hitsz.shoot_strategy;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class SurroundShoot implements ShootStrategy{
    public List<BaseBullet> shoot(HeroAircraft heroAircraft){
        List<BaseBullet> res = new LinkedList<>();
        heroAircraft.setShootNum(15);
        int direction = heroAircraft.getDirection();
        int shootNum = heroAircraft.getShootNum();
        int power = heroAircraft.getPower();
        int x = heroAircraft.getLocationX();
        int y = heroAircraft.getLocationY() + direction * 5;
        double radius = 20; // 环的半径
        double angleIncrement = 2 * Math.PI / shootNum; // 每颗子弹的角度增量

        for (int i = 0; i < shootNum; i++) {
            // 计算每颗子弹的角度
            double angle = i * angleIncrement;
            // 计算子弹的坐标
            int bulletX = (int) (x + radius * Math.cos(angle));
            int bulletY = (int) (y + radius * Math.sin(angle));
            // 计算子弹的速度
            int speedX = (int) (2 + direction * 5 * Math.cos(angle));
            int speedY = (int) (2 + direction * 5 * Math.sin(angle));
            // 创建子弹并添加到列表中
            BaseBullet bullet = new HeroBullet(bulletX, bulletY, speedX, speedY, power);
            res.add(bullet);
        }

        return res;
    }
}
