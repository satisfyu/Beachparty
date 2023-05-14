package satisfyu.beachparty.entity.chair;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ChairEntity extends Entity {
    public static final EntityDataAccessor<Boolean> LAYING = SynchedEntityData.defineId(ChairEntity.class, EntityDataSerializers.BOOLEAN);

    public ChairEntity(EntityType<?> type, Level world) {
        super(type, world);
        this.setLaying(false);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(LAYING, false);
    }

    public void setLaying(boolean laying) {
        this.entityData.set(LAYING, laying);
    }

    public boolean isLaying() {
        return this.entityData.get(LAYING);
    }

    @Override
    public Vec3 getDismountLocationForPassenger(LivingEntity passenger) {
        if (passenger instanceof Player p) {
            BlockPos pos = ChairUtil.getPreviousPlayerPosition(p, this);
            if (pos != null) {
                discard();
                return new Vec3(pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D);
            }
        }
        discard();
        return super.getDismountLocationForPassenger(passenger);
    }

    @Override
    public void remove(RemovalReason reason) {
        super.remove(reason);
        ChairUtil.removeChairEntity(level, blockPosition());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        this.setLaying(nbt.getBoolean("laying"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        nbt.putBoolean("laying", this.isLaying());
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }
}
