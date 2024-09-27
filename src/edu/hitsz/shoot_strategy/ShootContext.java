package edu.hitsz.shoot_strategy;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.bullet.BaseBullet;

import java.util.List;

public class ShootContext {

    public ShootContext(ShootStrategy strategy){
        this.strategy = strategy;
    }
    public void setStrategy(ShootStrategy strategy) {
        this.strategy = strategy;
    }
    public List<BaseBullet> executeStrategy(HeroAircraft heroAircraft){
        return strategy.shoot(heroAircraft);
    }

    private ShootStrategy strategy;

}
