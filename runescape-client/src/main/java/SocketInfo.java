import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedGetter;
import net.runelite.mapping.ObfuscatedName;

@ObfuscatedName("jo")
@Implements("SocketInfo")
public class SocketInfo {
	@ObfuscatedName("er")
	@ObfuscatedGetter(
		intValue = 1418794593
	)
	@Export("worldPort")
	static int worldPort;
}