module net.runelite.api {
	exports net.runelite.api;
	exports net.runelite.api.widgets;
	exports net.runelite.api.coords;
	exports net.runelite.api.events;
	exports net.runelite.api.vars;
	exports net.runelite.api.kit;
	exports net.runelite.api.util;

	requires static lombok;

	requires java.desktop;
	requires org.checkerframework.checker.qual;
	requires org.slf4j;
	requires com.google.common;
	requires org.apache.commons.lang3;
	requires org.apache.commons.text;
}