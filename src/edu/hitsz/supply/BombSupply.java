package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.music.MusicThread;
import edu.hitsz.shoot_strategy.ScatterShoot;

public class BombSupply extends BaseSupply {
    public BombSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    public void get_effect(HeroAircraft heroAircraft) {
        this.vanish();
        System.out.println("BombSupply active!");
        (new MusicThread("src/videos/bomb_explosion.wav")).start();
    }

}
