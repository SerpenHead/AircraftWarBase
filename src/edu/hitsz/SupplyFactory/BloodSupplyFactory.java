package edu.hitsz.SupplyFactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.supply.BloodSupply;

public class BloodSupplyFactory extends AbstractSupplyFactory {
    public BloodSupplyFactory() {
        super();
    }

    /*重写创造血包道具方法*/
    public BloodSupply creatBloodSupply(AbstractAircraft enemyAircraft) {
        return new BloodSupply(enemyAircraft.getLocationX(), enemyAircraft.getLocationY()
                , 0, 6);
    }
}
