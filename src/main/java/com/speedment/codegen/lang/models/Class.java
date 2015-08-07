/**
 *
 * Copyright (c) 2006-2015, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.codegen.lang.models;

import com.speedment.codegen.lang.interfaces.HasConstructors;
import com.speedment.codegen.lang.interfaces.HasSupertype;
import com.speedment.codegen.lang.models.implementation.ClassImpl;
import com.speedment.codegen.lang.models.modifiers.ClassModifier;
import java.util.function.Supplier;

/**
 * A model that represents a class in code.
 * 
 * @author Emil Forslund
 */
public interface Class extends ClassOrInterface<Class>, HasConstructors<Class>, 
    HasSupertype<Class>, ClassModifier<Class> {

    /**
     * Factory holder.
     */
    enum Factory { INST;
        private Supplier<Class> supplier = () -> new ClassImpl(null);
    }

    /**
     * Creates a new instance implementing this interface by using the class
     * supplied by the default factory. To change implementation, please use
     * the {@link #setSupplier(java.util.function.Supplier) setSupplier} method.
     * 
     * @param name  the name
     * @return      the new instance
     */
    static Class of(String name) {
        return Factory.INST.supplier.get().setName(name);
    }
        
    /**
     * Sets the instantiation method used to create new instances of this
     * interface.
     * 
     * @param supplier  the new constructor 
     */
    static void setSupplier(Supplier<Class> supplier) {
        Factory.INST.supplier = supplier;
    }
}