package net.runelite.mixins.rsps.ports;

import net.runelite.api.mixins.FieldHook;
import net.runelite.api.mixins.Inject;
import net.runelite.api.mixins.Mixin;
import net.runelite.rs.api.RSSocketInfo;

@Mixin(RSSocketInfo.class)
public abstract class WorldPortMixin implements RSSocketInfo {
	
	@FieldHook("worldPort")
	@Inject
	public void worldPortHook(int idx) {
		System.out.println("World port changed to " + getWorldPort());
	}
	
}