package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;

public class BloodSupply extends BaseSupply {
    private final int blood_recovered = 30;

    public BloodSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    public void get_effect(HeroAircraft heroAircraft) {
        this.vanish();
        heroAircraft.increaseHp(blood_recovered);
        System.out.println("BloodSupply active!");
    }

}
