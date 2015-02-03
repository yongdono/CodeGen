/*
 * Copyright 2015 Emil Forslund.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.speedment.codegen.java.models.modifiers;

import static com.speedment.codegen.java.models.modifiers.Modifier_.*;

/**
 *
 * @author Emil Forslund
 * @param <T>
 */
public interface ClassModifier<T extends ClassModifier<T>> extends Modifiable<T> {
	default T public_() {
		getModifiers().add(PUBLIC);
		return (T) this;
	}
	
	default T protected_() {
		getModifiers().add(PROTECTED);
		return (T) this;
	}
	
	default T private_() {
		getModifiers().add(PRIVATE);
		return (T) this;
	}
	
	default T abstract_() {
		getModifiers().add(ABSTRACT);
		return (T) this;
	}
	
	default T static_() {
		getModifiers().add(STATIC);
		return (T) this;
	}
	
	default T final_() {
		getModifiers().add(FINAL);
		return (T) this;
	}
	
	default T strictfp_() {
		getModifiers().add(STRICTFP);
		return (T) this;
	}
}
