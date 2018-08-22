package net.liggesmeyer.arm.Group;

import net.liggesmeyer.arm.regions.RegionKind;

public class RegionKindLimit {
    private RegionKind regionKind;
    private int limit;

    RegionKindLimit(RegionKind regionKind, int limit) {
        this.regionKind = regionKind;
        this.limit = limit;
        if(this.limit == -1){
            this.limit = Integer.MAX_VALUE;
        }
    }

    public RegionKind getRegionKind(){
        return this.regionKind;
    }

    public int getLimit() {
        return this.limit;
    }

}