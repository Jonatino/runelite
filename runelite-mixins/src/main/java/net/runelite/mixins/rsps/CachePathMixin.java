package net.runelite.mixins.rsps;

import net.runelite.api.mixins.Inject;
import net.runelite.api.mixins.MethodHook;
import net.runelite.api.mixins.Mixin;
import net.runelite.api.mixins.Shadow;
import net.runelite.rs.api.RSClient;

import java.io.File;

@Mixin(RSClient.class)
public abstract class CachePathMixin implements RSClient {
	
	@Inject
	private static final String PVPHERO_CACHE_PATH = System.getProperty(
			"user.home") + File.separator + "pvphero" + File.separator + "cache" + File.separator + "LIVE";
	
	@Shadow("client")
	static RSClient client;
	
	@Shadow("cacheDir")
	private static File cacheDir;
	
	@MethodHook(value = "init", end = true)
	@Inject
	public void checkCachePath() {
		if (!cacheDir.getPath().equals(PVPHERO_CACHE_PATH)) {
			throw new RuntimeException(
					"Cache path is wrong. expected=" + PVPHERO_CACHE_PATH + ", actual=" + cacheDir.getPath());
		}
	}
	
}