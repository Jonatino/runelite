package net.runelite.mixins;

import net.runelite.api.HeadIcon;
import static net.runelite.api.HeadIcon.MAGIC;
import static net.runelite.api.HeadIcon.MELEE;
import static net.runelite.api.HeadIcon.RANGED;
import static net.runelite.api.HeadIcon.RANGE_MAGE;
import net.runelite.api.events.NpcActionChanged;
import net.runelite.api.mixins.Copy;
import net.runelite.api.mixins.FieldHook;
import net.runelite.api.mixins.Inject;
import net.runelite.api.mixins.Mixin;
import net.runelite.api.mixins.Replace;
import net.runelite.api.mixins.Shadow;
import net.runelite.rs.api.RSBuffer;
import net.runelite.rs.api.RSClient;
import net.runelite.rs.api.RSNPCComposition;

@Mixin(RSNPCComposition.class)
public abstract class RSNPCCompositionMixin implements RSNPCComposition
{
	@Shadow("client")
	private static RSClient client;

	@Inject
	@Override
	public HeadIcon getOverheadIcon()
	{
		switch (getRsOverheadIcon())
		{
			case 0:
				return MELEE;
			case 1:
				return RANGED;
			case 2:
				return MAGIC;
			case 6:
				return RANGE_MAGE;
			default:
				return null;
		}
	}

	@FieldHook("actions")
	@Inject
	public void actionsHook(int idx)
	{
		NpcActionChanged npcActionChanged = new NpcActionChanged();
		npcActionChanged.setNpcComposition(this);
		npcActionChanged.setIdx(idx);
		client.getCallbacks().post(npcActionChanged);
	}

	@Copy("decodeNext")
	@Replace("decodeNext")
	public void copy$decodeNext(RSBuffer buffer, int opcode)
	{
		if (opcode == 250)
		{
			int size = buffer.readUnsignedByte();
			for (int i = 0; i < size; i++) {
				buffer.readStringCp1252NullTerminated();
				buffer.readStringCp1252NullTerminated();
			}
		}
		else
		{
			copy$decodeNext(buffer, opcode);
		}
	}
}