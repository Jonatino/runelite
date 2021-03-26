module net.runelite.cache {
	exports net.runelite.cache;
	exports net.runelite.cache.definitions;
	exports net.runelite.cache.fs;
	exports net.runelite.cache.util;

	requires static lombok;

	requires org.slf4j;
	requires com.google.gson;
	requires java.desktop;
	requires net.runelite.http.api;
}