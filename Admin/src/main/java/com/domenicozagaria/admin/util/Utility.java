package com.domenicozagaria.admin.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;

public class Utility {

    private static final int PAGE_SIZE = 50;
    public static <T> Optional<T> findEntityById(JpaRepository<T, Integer> repository, Integer id) {
        return repository.findById(id);
    }

    public static <T> void saveEntity(JpaRepository<T, Integer> repository, T entity) {
        repository.save(entity);
    }

    public static <T> void deleteEntity(JpaRepository<T, Integer> repository, T entity) {
        repository.delete(entity);
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

    public static boolean checkInBetween(LocalDateTime toCheck, LocalDateTime start, LocalDateTime end) {
        return !toCheck.isBefore(start) && !toCheck.isAfter(end);
    }

    public static int getDefaultPageSize() {
        return PAGE_SIZE;
    }

    public static Pageable getPageable(int pageNumber) {
        return PageRequest.of(pageNumber, getDefaultPageSize());
    }
}
