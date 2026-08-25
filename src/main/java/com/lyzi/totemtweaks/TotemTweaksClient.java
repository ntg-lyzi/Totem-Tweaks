package com.lyzi.totemtweaks;

import com.lyzi.totemtweaks.config.TotemTweaksConfig;
import com.lyzi.totemtweaks.gui.TotemTweaksScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TotemTweaksClient implements ClientModInitializer {

public static final String MOD_ID = "totemtweaks";
public static final Logger LOGGER = LoggerFactory.getLogger("Totem Tweaks");

@Override
public void onInitializeClient() {
ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
dispatcher.register(ClientCommandManager.literal("totemtweaks")
.executes(context -> {
MinecraftClient.getInstance().setScreen(new TotemTweaksScreen());
return 1;
})));

ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
if (!(screen instanceof InventoryScreen)) {
return;
}
if (!TotemTweaksConfig.get().enabled) {
return;
}
selectTotemSlotIfSafe(client);
});
}

private void selectTotemSlotIfSafe(MinecraftClient client) {
ClientPlayerEntity player = client.player;
if (player == null) {
return;
}

PlayerInventory inventory = player.getInventory();
int totemSlot = -1;
int totemCount = 0;

for (int i = 0; i < 9; i++) {
ItemStack stack = inventory.getStack(i);
if (stack.isOf(Items.TOTEM_OF_UNDYING)) {
totemCount++;
totemSlot = i;
}
}

if (totemCount != 1) {
return;
}

if (inventory.getSelectedSlot() == totemSlot) {
return;
}

inventory.setSelectedSlot(totemSlot);
if (player.networkHandler != null) {
player.networkHandler.sendPacket(new UpdateSelectedSlotC2SPacket(totemSlot));
}
}
}
