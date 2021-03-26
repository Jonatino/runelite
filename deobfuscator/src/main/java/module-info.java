module net.runelite.deobfuscator {
	exports net.runelite.asm;
	exports net.runelite.asm.pool;
	exports net.runelite.asm.attributes;
	exports net.runelite.asm.attributes.code;
	exports net.runelite.asm.attributes.code.instructions;
	exports net.runelite.asm.signature;
	exports net.runelite.deob;
	exports net.runelite.deob.deobfuscators.arithmetic;
	exports net.runelite.deob.util;
	exports net.runelite.asm.attributes.code.instruction.types;
	exports net.runelite.asm.execution;
	requires static lombok;

	requires net.runelite.annotations;
	requires org.slf4j;
	requires org.objectweb.asm;
	requires org.checkerframework.checker.qual;
	requires com.google.gson;
	requires com.google.common;
	requires org.objectweb.asm.util;
	requires net.runelite.runescape.api;
}