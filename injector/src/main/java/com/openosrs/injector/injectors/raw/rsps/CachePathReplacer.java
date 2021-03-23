/*
 * Copyright (c) 2019, Lucas <https://github.com/Lucwousin>
 * All rights reserved.
 *
 * This code is licensed under GPL3, see the complete license in
 * the LICENSE file in the root directory of this submodule.
 *
 * Copyright (c) 2018 Abex
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.openosrs.injector.injectors.raw.rsps;

import com.openosrs.injector.InjectUtil;
import com.openosrs.injector.injection.InjectData;
import com.openosrs.injector.injectors.AbstractInjector;
import net.runelite.asm.Method;
import net.runelite.asm.attributes.code.Instruction;
import net.runelite.asm.attributes.code.instructions.LDC;
import net.runelite.asm.execution.Execution;
import net.runelite.asm.execution.InstructionContext;
import net.runelite.asm.execution.MethodContext;

import java.util.concurrent.atomic.AtomicReference;

public class CachePathReplacer extends AbstractInjector {
	public CachePathReplacer(InjectData inject) {
		super(inject);
	}
	
	int linesReplaced = 0;
	
	public void inject() {
		replaceRandomDat();
		replaceOldschoolString();
		replaceJagexStrings();
		
		if (linesReplaced != 5) {
			throw new RuntimeException("Failed to replace cache path. " + linesReplaced);
		}
	}
	
	public void replaceRandomDat() {
		final Method draw = InjectUtil.findMethod(inject, "findOrCreateRandomDatFile", "Varcs", null, true, false);
		
		Execution e = new Execution(inject.getVanilla());
		e.addMethod(draw);
		e.noInvoke = true;
		
		AtomicReference<MethodContext> atomicMethodContext = new AtomicReference<>(null);
		
		e.addMethodContextVisitor(atomicMethodContext::set);
		e.run();
		
		
		MethodContext methodContext = atomicMethodContext.get();
		for (InstructionContext instrCtx : methodContext.getInstructionContexts()) {
			Instruction instr = instrCtx.getInstruction();
			
			if (instr instanceof LDC) {
				LDC ldc = (LDC) instr;
				Object constant = ldc.getConstant();
				if (constant instanceof String) {
					if (constant.equals("random.dat")) {
						ldc.setConstant("pvphero_random.dat");
						linesReplaced++;
					}
				}
			}
		}
	}
	
	public void replaceOldschoolString() {
		final Method draw = InjectUtil.findMethod(inject, "init", "Client", null, true, false);
		
		Execution e = new Execution(inject.getVanilla());
		e.addMethod(draw);
		e.noInvoke = true;
		
		AtomicReference<MethodContext> atomicMethodContext = new AtomicReference<>(null);
		
		e.addMethodContextVisitor(atomicMethodContext::set);
		e.run();
		
		
		MethodContext methodContext = atomicMethodContext.get();
		for (InstructionContext instrCtx : methodContext.getInstructionContexts()) {
			Instruction instr = instrCtx.getInstruction();
			
			if (instr instanceof LDC) {
				LDC ldc = (LDC) instr;
				Object constant = ldc.getConstant();
				if (constant instanceof String) {
					if (constant.equals("oldschool")) {
						ldc.setConstant("cache");
						linesReplaced++;
					}
				}
			}
		}
	}
	
	public void replaceJagexStrings() {
		final Method draw = InjectUtil.findMethod(inject, "findAndLoadCache", "Projectile", null, true, false);
		
		Execution e = new Execution(inject.getVanilla());
		e.addMethod(draw);
		e.noInvoke = true;
		
		AtomicReference<MethodContext> atomicMethodContext = new AtomicReference<>(null);
		
		e.addMethodContextVisitor(atomicMethodContext::set);
		e.run();
		
		
		MethodContext methodContext = atomicMethodContext.get();
		for (InstructionContext instrCtx : methodContext.getInstructionContexts()) {
			Instruction instr = instrCtx.getInstruction();
			
			if (instr instanceof LDC) {
				LDC ldc = (LDC) instr;
				Object constant = ldc.getConstant();
				if (constant instanceof String) {
					if (constant.equals("jagex_cl_")) {
						ldc.setConstant("pvphero_cl_");
					} else if (constant.equals("jagexcache")) {
						ldc.setConstant("pvphero");
					} else {
						continue;
					}
					linesReplaced++;
				}
			}
		}
	}
}