package net.runelite.mixins.rsps.ports;

import net.runelite.api.mixins.FieldHook;
import net.runelite.api.mixins.Inject;
import net.runelite.api.mixins.Mixin;
import net.runelite.rs.api.RSPlayers;

@Mixin(RSPlayers.class)
public abstract class Js5PortMixin implements RSPlayers {
	
	@FieldHook("js5Port")
	@Inject
	public void js5PortHook(int idx) {
		System.out.println("js5 port changed to " + getJs5Port());
	}
	
}