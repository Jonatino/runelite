package net.runelite.rs.api;

import net.runelite.api.MouseRecorder;
import net.runelite.mapping.Import;

public interface RSSocketInfo
{
	@Import("worldPort")
	int getWorldPort();
	
	@Import("worldPort")
	void setWorldPort(int worldPort);
	
}