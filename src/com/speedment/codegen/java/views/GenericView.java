/*
 * Copyright 2015 Duncan.
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
package com.speedment.codegen.java.views;

import com.speedment.codegen.base.CodeGenerator;
import com.speedment.codegen.base.CodeView;
import com.speedment.codegen.java.models.Generic_;
import java.util.Optional;
import com.speedment.util.$;
import static com.speedment.codegen.Formatting.*;
import com.speedment.util.CodeCombiner;

/**
 *
 * @author Duncan
 */
public class GenericView implements CodeView<Generic_> {
	private final static CharSequence EXTENDS_STRING = " extends ";

	@Override
	public Optional<CharSequence> render(CodeGenerator cg, Generic_ model) {
		if (!model.getLowerBound().isPresent() 
		&&   model.getUpperBounds().isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(new $(
				model.getLowerBound().orElse(EMPTY),
				cg.onEach(model.getUpperBounds()).collect(
					CodeCombiner.joinIfNotEmpty(
						AND, 
						model.getLowerBound().isPresent() ? EXTENDS_STRING : EMPTY, 
						EMPTY
					)
				)
			));
		}
	}
}