package net.runelite.mixins.rsps;

import net.runelite.api.mixins.Inject;
import net.runelite.api.mixins.MethodHook;
import net.runelite.api.mixins.Mixin;
import net.runelite.api.mixins.Shadow;
import net.runelite.rs.api.RSClient;

import java.math.BigInteger;

@Mixin(RSClient.class)
public abstract class SocketMixin implements RSClient {
	
	@Inject
	public static final int DEFAULT_WORLD_PORT = 54900;
	
	@Inject
	public static final int DEFAULT_JS5_PORT = 54901;
	
	@Shadow("currentPort")
	private static int currentPort;
	
	@Shadow("worldHost")
	private static String worldHost;
	
	@Shadow("modulus")
	private static BigInteger modulus;
	
	@Inject
	@MethodHook("doCycleJs5Connect")
	public void doCycleJs5Connect() {
		currentPort = DEFAULT_JS5_PORT;
		worldHost = "js5.pvpherodev.com";
	}
	
	@Inject
	@MethodHook("doCycleLoggedOut")
	public void doCycleLoggedOut() {
		currentPort = DEFAULT_WORLD_PORT;
		worldHost = "127.0.0.1";
	}
}