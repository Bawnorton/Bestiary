package com.bawnorton;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bestiary implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("bestiary");

	@Override
	public void onInitialize() {
		LOGGER.info("Server initialized");
	}
}