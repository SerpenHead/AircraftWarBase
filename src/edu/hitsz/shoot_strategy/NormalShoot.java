package edu.hitsz.shoot_strategy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;
import edu.hitsz.bullet.HeroBullet;
import java.util.LinkedList;
import java.util.List;

public class NormalShoot implements ShootStrategy{
    public List<BaseBullet> shoot(HeroAircraft heroAircraft){
        List<BaseBullet> res = new LinkedList<>();
        heroAircraft.setShootNum(1);
        int direction = heroAircraft.getDirection();
        int shootNum = heroAircraft.getShootNum();
        int power = heroAircraft.getPower();
        int x = heroAircraft.getLocationX();
        int y = heroAircraft.getLocationY() + direction * 2;
        int speedX = 0;
        int speedY = heroAircraft.getSpeedY() + direction * 5;
        BaseBullet bullet;
        for (int i = 0; i < shootNum; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new HeroBullet(x + (i * 2 - shootNum + 1) * 10, y, speedX, speedY + i, power);
            res.add(bullet);
        }
        return res;
    }

}
