module net.runelite.http.api {
	exports net.runelite.http.api;
	exports net.runelite.http.api.xtea;
	exports net.runelite.http.api.worlds;
	exports net.runelite.http.api.ws;
	exports net.runelite.http.api.ws.messages.party;
	exports net.runelite.http.api.ws.messages;
	exports net.runelite.http.api.npc;
	exports net.runelite.http.api.hiscore;
	exports net.runelite.http.api.item;
	exports net.runelite.http.api.config;
	exports net.runelite.http.api.account;
	exports net.runelite.http.api.feed;
	exports net.runelite.http.api.chat;

	requires static lombok;

	requires net.runelite.api;
	requires com.google.gson;
	requires okhttp3;
	requires java.desktop;
	requires org.slf4j;
}