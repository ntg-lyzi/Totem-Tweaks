package com.lyzi.totemtweaks.gui;

import com.lyzi.totemtweaks.config.TotemTweaksConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

/**
 * Only ButtonWidget is used for interaction here (no mouseClicked/keyPressed
 * overrides), so this stays stable across Minecraft versions.
 */
public class TotemTweaksScreen extends Screen {

	private static final int COLOR_BG_DIM   = 0xC0000000;
	private static final int COLOR_PANEL    = 0xFF181818;
	private static final int COLOR_HEADER   = 0xFF0C0C0C;
	private static final int COLOR_ACCENT   = 0xFFFFA500;
	private static final int COLOR_TEXT     = 0xFFE6E6E6;
	private static final int COLOR_TEXT_DIM = 0xFF9B9B9B;
	private static final int COLOR_BORDER   = 0xFF2E2E2E;

	private static final int PANEL_WIDTH = 280;
	private static final int PANEL_HEIGHT = 130;

	private int panelX, panelY;

	public TotemTweaksScreen() {
		super(Text.literal("Totem Tweaks"));
	}

	@Override
	protected void init() {
		panelX = (this.width - PANEL_WIDTH) / 2;
		panelY = (this.height - PANEL_HEIGHT) / 2;

		this.addDrawableChild(ButtonWidget.builder(toggleLabel(), b -> {
			TotemTweaksConfig cfg = TotemTweaksConfig.get();
			cfg.enabled = !cfg.enabled;
			cfg.save();
			b.setMessage(toggleLabel());
		}).dimensions(panelX + 20, panelY + 60, PANEL_WIDTH - 40, 20).build());

		this.addDrawableChild(ButtonWidget.builder(Text.literal("Close"), b -> this.close())
				.dimensions(panelX + 20, panelY + PANEL_HEIGHT - 28, PANEL_WIDTH - 40, 20).build());
	}

	private Text toggleLabel() {
		boolean on = TotemTweaksConfig.get().enabled;
		return Text.literal("Auto-select Totem Slot: " + (on ? "On" : "Off"));
	}

	@Override
	public void render(DrawContext context, int mouseX, int mouseY, float delta) {
		context.fill(0, 0, this.width, this.height, COLOR_BG_DIM);

		context.fill(panelX - 1, panelY - 1, panelX + PANEL_WIDTH + 1, panelY + PANEL_HEIGHT + 1, COLOR_BORDER);
		context.fill(panelX, panelY, panelX + PANEL_WIDTH, panelY + PANEL_HEIGHT, COLOR_PANEL);

		context.fillGradient(panelX, panelY, panelX + PANEL_WIDTH, panelY + 26, COLOR_HEADER, 0xFF141414);
		context.drawText(this.textRenderer, Text.literal("Totem Tweaks"), panelX + 10, panelY + 9, COLOR_ACCENT, true);

		context.drawText(this.textRenderer,
				Text.literal("Jumps your held item to your Totem"),
				panelX + 16, panelY + 34, COLOR_TEXT_DIM, false);
		context.drawText(this.textRenderer,
				Text.literal("when you open your inventory."),
				panelX + 16, panelY + 45, COLOR_TEXT_DIM, false);

		super.render(context, mouseX, mouseY, delta);
	}

	@Override
	public boolean shouldPause() {
		return false;
	}
}
