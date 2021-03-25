package net.runelite.mixins;

import net.runelite.api.events.PostItemComposition;
import net.runelite.api.mixins.Copy;
import net.runelite.api.mixins.Inject;
import net.runelite.api.mixins.MethodHook;
import net.runelite.api.mixins.Mixin;
import net.runelite.api.mixins.Replace;
import net.runelite.api.mixins.Shadow;
import net.runelite.rs.api.RSBuffer;
import net.runelite.rs.api.RSClient;
import net.runelite.rs.api.RSItemComposition;
import net.runelite.rs.api.RSModel;

import java.math.BigInteger;

@Mixin(RSItemComposition.class)
public abstract class RSItemCompositionMixin implements RSItemComposition
{
	private static final int DEFAULT_CUSTOM_SHIFT_CLICK_INDEX = -2;

	@Shadow("client")
	private static RSClient client;

	@Inject
	private int shiftClickActionIndex = DEFAULT_CUSTOM_SHIFT_CLICK_INDEX;

	@Inject
	private int modelOverride = -1;

	@Override
	@Inject
	public void setModelOverride(int id)
	{
		modelOverride = id;
	}

	@Inject
	RSItemCompositionMixin()
	{
	}

	@Inject
	@Override
	public boolean isStackable()
	{
		return getIsStackable() != 0;
	}

	@Inject
	@Override
	public void setShiftClickActionIndex(int shiftClickActionIndex)
	{
		this.shiftClickActionIndex = shiftClickActionIndex;
	}

	@Copy("getShiftClickIndex")
	@Replace("getShiftClickIndex")
	public int copy$getShiftClickActionIndex()
	{
		return shiftClickActionIndex == DEFAULT_CUSTOM_SHIFT_CLICK_INDEX ? copy$getShiftClickActionIndex() : shiftClickActionIndex;
	}

	@Inject
	@Override
	public void resetShiftClickActionIndex()
	{
		shiftClickActionIndex = DEFAULT_CUSTOM_SHIFT_CLICK_INDEX;
	}

	@Inject
	@MethodHook(value = "post", end = true)
	public void post()
	{
		final PostItemComposition event = new PostItemComposition();
		event.setItemComposition(this);
		client.getCallbacks().post(event);
	}

	@Copy("getModel")
	@Replace("getModel")
	public RSModel copy$getModel(int quantity)
	{
		if (modelOverride == -1)
		{
			return copy$getModel(quantity);
		}

		return client.getRSItemDefinition(modelOverride).getModel(quantity);
	}

	@Inject
	@Override
	public int getHaPrice()
	{
		int price = getPrice();
		return (int) ((float) price * 0.6f);
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