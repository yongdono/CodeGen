package com.speedment.util;

import static com.speedment.codegen.Formatting.EMPTY;
import java.util.stream.Collector;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.StringJoiner;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 * @author Duncan
 */
public class CodeCombiner {
	public static Collector<String, ?, String> joinIfNotEmpty(String delimiter) {
		return joinIfNotEmpty(delimiter, EMPTY, EMPTY);
	}
	
	public static Collector<String, ?, String> joinIfNotEmpty(String delimiter, String prefix, String suffix) {
		return new CollectorImpl<>(
			() -> new StringJoiner(delimiter),
			StringJoiner::add, 
			StringJoiner::merge,
			s -> s.length() > 0 ? 
				prefix + s + suffix : 
				s.toString(), 
			Collections.emptySet()
		);
	}
	
	public static Optional<String> ifEmpty(String str) {
		if (str == null || 0 == str.length()) {
			return Optional.empty();
		} else {
			return Optional.of(str);
		}
	}
	
	/**
     * Simple implementation class for {@code Collector}.
     *
     * @param <T> the type of elements to be collected
     * @param <R> the type of the result
     */
    static class CollectorImpl<T, A, R> implements Collector<T, A, R> {
        private final Supplier<A> supplier;
        private final BiConsumer<A, T> accumulator;
        private final BinaryOperator<A> combiner;
        private final Function<A, R> finisher;
        private final Set<Collector.Characteristics> characteristics;

        CollectorImpl(Supplier<A> supplier,
                      BiConsumer<A, T> accumulator,
                      BinaryOperator<A> combiner,
                      Function<A,R> finisher,
                      Set<Collector.Characteristics> characteristics) {
            this.supplier = supplier;
            this.accumulator = accumulator;
            this.combiner = combiner;
            this.finisher = finisher;
            this.characteristics = characteristics;
        }

        CollectorImpl(Supplier<A> supplier,
                      BiConsumer<A, T> accumulator,
                      BinaryOperator<A> combiner,
                      Set<Collector.Characteristics> characteristics) {
            this (supplier, accumulator, combiner, castingIdentity(), characteristics);
        }

        @Override
        public BiConsumer<A, T> accumulator() {
            return accumulator;
        }

        @Override
        public Supplier<A> supplier() {
            return supplier;
        }

        @Override
        public BinaryOperator<A> combiner() {
            return combiner;
        }

        @Override
        public Function<A, R> finisher() {
            return finisher;
        }

        @Override
        public Set<Collector.Characteristics> characteristics() {
            return characteristics;
        }
    }
	
	private static <I, R> Function<I, R> castingIdentity() {
        return i -> (R) i;
    }
}
