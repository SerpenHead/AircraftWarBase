package edu.hitsz.supply;

import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.shoot_strategy.NormalShoot;
import edu.hitsz.shoot_strategy.ScatterShoot;
import edu.hitsz.shoot_strategy.SurroundShoot;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FirePlusSupply extends BaseSupply {
    private Thread nowThread;
    public FirePlusSupply(int locationX, int locationY, int speedX, int speedY) {
        super(locationX, locationY, speedX, speedY);
    }

    public void get_effect(HeroAircraft heroAircraft) {
        this.vanish();
        Runnable r = () -> {
            try {
                heroAircraft.setShootStrategy(new SurroundShoot());
                Thread.sleep(5000);
                heroAircraft.setShootStrategy(new NormalShoot());
            } catch (InterruptedException ignore) {
            }

        };
        if(nowThread != null) {
            nowThread.interrupt();
        }
        nowThread = new Thread(r);
        nowThread.start();
        System.out.println("FirePlusSupply active!");
    }
}
