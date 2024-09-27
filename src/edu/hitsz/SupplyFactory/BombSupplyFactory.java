package edu.hitsz.SupplyFactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.supply.BombSupply;

public class BombSupplyFactory extends AbstractSupplyFactory {
    public BombSupplyFactory() {
        super();
    }

    ;
    /*重写创造炸弹道具方法*/
    public BombSupply creatBombSupply(AbstractAircraft enemyAircraft) {
        return new BombSupply(enemyAircraft.getLocationX(), enemyAircraft.getLocationY()
                , 0, 6);
    }


}
