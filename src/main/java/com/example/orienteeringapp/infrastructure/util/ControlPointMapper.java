package com.example.orienteeringapp.infrastructure.util;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ControlPointMapper {
    public static <T, R> List<R> mapList(List<T> source, Function<T, R> mapper) {
        if (source == null) {
            return Collections.emptyList();
        }
        return source.stream().map(mapper).collect(Collectors.toList());
    }
}
