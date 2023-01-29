package com.domenicozagaria.admin.util;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;

public class Utility {
    public static <T> Optional<T> findEntityById(JpaRepository<T, Integer> repository, Integer id) {
        return repository.findById(id);
    }

    public static LocalDateTime getTodayWithDefaultTimezone() {
        return LocalDateTime.now(getDefaultTimezone());
    }

    public static ZoneId getDefaultTimezone() {
        return ZoneId.of("Europe/Rome");
    }

    public static <T, K, A, R> R mapCollectionTo(Collection<T> toMap, Function<T, K> function, Collector<K, A, R> collector) {
        return toMap.stream()
                .map(function)
                .collect(collector);
    }
}
