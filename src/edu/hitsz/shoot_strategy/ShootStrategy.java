package edu.hitsz.shoot_strategy;
import edu.hitsz.bullet.*;
import edu.hitsz.aircraft.HeroAircraft;
import java.util.List;
public interface ShootStrategy {
    List<BaseBullet> shoot(HeroAircraft heroAircraft);
}
