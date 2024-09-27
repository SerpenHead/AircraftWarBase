package edu.hitsz.SupplyFactory;

import edu.hitsz.supply.BloodSupply;
import edu.hitsz.supply.BombSupply;
import edu.hitsz.supply.FirePlusSupply;
import edu.hitsz.supply.FireSupply;
/*定义抽象类*/
public abstract class AbstractSupplyFactory {
    public AbstractSupplyFactory() {
    }

    ;
    /*创造四种道具*/
    public BloodSupply creatBloodSupply() {
        return null;
    }

    public BombSupply creatBombSupply() {
        return null;
    }

    public FireSupply creatFireSupply() {
        return null;
    }


    public FirePlusSupply creatFirePlusSupply() { return null; }
}
