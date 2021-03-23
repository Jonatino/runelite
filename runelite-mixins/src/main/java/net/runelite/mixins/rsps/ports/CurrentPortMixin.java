package net.runelite.mixins.rsps.ports;

import net.runelite.api.mixins.FieldHook;
import net.runelite.api.mixins.Inject;
import net.runelite.api.mixins.Mixin;
import net.runelite.rs.api.RSArchiveDiskAction;

@Mixin(RSArchiveDiskAction.class)
public abstract class CurrentPortMixin implements RSArchiveDiskAction {
	
	@FieldHook("currentPort")
	@Inject
	public void currentPortHook(int idx) {
		//System.out.println("current port changed to " + getCurrentPort());
	}
	
}