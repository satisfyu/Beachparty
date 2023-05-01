package satisfyu.beachparty.item;


import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class PoolNoodleItem extends SwordItem implements DyeableLeatherItem {

    public PoolNoodleItem(Tier toolMaterial, int attackDamage, float attackSpeed, Properties properties) {
        super(toolMaterial, attackDamage, attackSpeed, properties);
    }
}