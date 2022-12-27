package com.ceaselesscabbage.draconicmodularexpansion.items;

import com.brandon3055.brandonscore.lib.TechPropBuilder;
import com.brandon3055.draconicevolution.api.config.BooleanProperty;
import com.brandon3055.draconicevolution.api.modules.lib.ModuleHostImpl;
import com.brandon3055.draconicevolution.init.DEContent;
import com.brandon3055.draconicevolution.init.ModuleCfg;
import com.brandon3055.draconicevolution.integration.equipment.EquipmentManager;
import com.brandon3055.draconicevolution.items.tools.DraconiumCapacitor;

import net.minecraft.item.ItemStack;

public class ModifiedModularCapacitor extends DraconiumCapacitor{

	public ModifiedModularCapacitor(TechPropBuilder properties) {
		super(properties);
	}
	
	 @Override
	    public ModuleHostImpl createHost(ItemStack stack) {
	        ModuleHostImpl host;
	        if (this == DEContent.capacitor_creative) {
	            host = new ModuleHostImpl(getTechLevel(), 1, 1, "capacitor", ModuleCfg.removeInvalidModules);
	        }else {
	            host = new ModuleHostImpl(getTechLevel(), 4, 3, "capacitor", ModuleCfg.removeInvalidModules);
	        }
	        host.addPropertyBuilder(props -> {
	            props.add(new BooleanProperty("charge_held_item", false));
	            props.add(new BooleanProperty("charge_armor", false));
	            props.add(new BooleanProperty("charge_hot_bar", false));
	            props.add(new BooleanProperty("charge_main", false));
	            if (EquipmentManager.equipModLoaded()) {
	                props.add(new BooleanProperty("charge_" + EquipmentManager.equipModID(), false));
	            }
	        });
	        return host;
	    }

}
