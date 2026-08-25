package com.lyzi.totemtweaks.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TotemTweaksConfig {

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final Path CONFIG_DIR = FabricLoader.getInstance().getConfigDir().resolve("totemtweakslyzi");
	private static final Path CONFIG_FILE = CONFIG_DIR.resolve("config.json");

	public boolean enabled = true;

	private static TotemTweaksConfig instance;

	public static TotemTweaksConfig get() {
		if (instance == null) {
			instance = load();
		}
		return instance;
	}

	private static TotemTweaksConfig load() {
		try {
			Files.createDirectories(CONFIG_DIR);
			if (Files.exists(CONFIG_FILE)) {
				try (Reader reader = Files.newBufferedReader(CONFIG_FILE, StandardCharsets.UTF_8)) {
					TotemTweaksConfig cfg = GSON.fromJson(reader, TotemTweaksConfig.class);
					if (cfg != null) {
						return cfg;
					}
				}
			}
		} catch (IOException ignored) {
		}
		TotemTweaksConfig fresh = new TotemTweaksConfig();
		fresh.save();
		return fresh;
	}

	public void save() {
		try {
			Files.createDirectories(CONFIG_DIR);
			try (Writer writer = Files.newBufferedWriter(CONFIG_FILE, StandardCharsets.UTF_8)) {
				GSON.toJson(this, writer);
			}
		} catch (IOException ignored) {
		}
	}
}
