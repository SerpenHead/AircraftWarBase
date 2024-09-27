package edu.hitsz.SupplyFactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.supply.FirePlusSupply;

public class FirePlusSupplyFactory extends AbstractSupplyFactory{
    public FirePlusSupplyFactory(){ super();}

    public FirePlusSupply creatFirePlusSupply(AbstractAircraft enemyAircraft) {
        return new FirePlusSupply(enemyAircraft.getLocationX(), enemyAircraft.getLocationY()
                , 0, 6);
    }
}
