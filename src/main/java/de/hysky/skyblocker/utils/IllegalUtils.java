package de.hysky.skyblocker.utils;

import com.mojang.logging.LogUtils;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.ClickSlotC2SPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import org.slf4j.Logger;

public final class IllegalUtils {

	/**
	 * Returns true if click worked, false if not
	 */
	public static void sendMiddleClick(GenericContainerScreen screen, int index) {

		ClientPlayerEntity player = MinecraftClient.getInstance().player;
		if (player == null) return;

		ScreenHandler screenHandler = screen.getScreenHandler();
		Slot slot = screenHandler.getSlot(index);
		if (slot == null) return;

		ItemStack stack = slot.getStack();
		Int2ObjectMap<ItemStack> modifiedStacks = Int2ObjectMaps.emptyMap();

		ClientPlayNetworkHandler networkHandler = MinecraftClient.getInstance().getNetworkHandler();
		if (networkHandler == null) return;

		ClickSlotC2SPacket packet = new ClickSlotC2SPacket(
				screenHandler.syncId,
				screenHandler.getRevision(),
				index,
				0,
				SlotActionType.CLONE,
				stack,
				modifiedStacks
		);
		networkHandler.sendPacket(packet);
	}

}
