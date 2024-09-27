package edu.hitsz.SupplyFactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.supply.FireSupply;

public class FireSupplyFactory extends AbstractSupplyFactory {
    public FireSupplyFactory() {
        super();
    }
    /*重写创造火力道具方法*/
    public FireSupply creatFireSupply(AbstractAircraft enemyAircraft) {
        return new FireSupply(enemyAircraft.getLocationX(), enemyAircraft.getLocationY()
                , 0, 6);
    }
}
