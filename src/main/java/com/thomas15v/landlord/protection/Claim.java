package com.thomas15v.landlord.protection;

import com.flowpowered.math.vector.Vector3i;
import com.thomas15v.landlord.LandLordPlugin;
import ninja.leaping.configurate.objectmapping.Setting;
import org.spongepowered.api.entity.player.Player;

import java.io.Serializable;
import java.util.UUID;

public class Claim implements Serializable {

    //todo: Change this to UUID
    @Setting
    private String tenantId;

    @Setting
    private long claimed;

    private TenantManager tenantManager;
    private Vector3i location;


    public Claim(Tenant tenant, TenantManager tenantManager, Vector3i location){
        this.tenantManager = tenantManager;
        this.location = location;
        this.tenantId = tenant.getId().toString();
        this.claimed = System.currentTimeMillis();
    }

    public Claim(Vector3i location) {
        this.location = location;
        this.tenantManager = LandLordPlugin.getInstance().getTenantManager();
    }

    public Vector3i getLocation() {
        return location;
    }

    public Tenant getOwner(){
        return tenantManager.getTentant(UUID.fromString(tenantId)).get();
    }

    public int getX(){
        return location.getX();
    }

    public int getZ(){
        return location.getZ();
    }

    public boolean canAcces(Player player){
        //todo: add claim trusts
        return getOwner().trusts(player.getUniqueId());
    }

}
