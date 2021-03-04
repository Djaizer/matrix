package com.matrix.utils;

import com.matrix.view.Arrow;
import com.matrix.view.Service;
import com.matrix.view.ServiceStatus;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MatrixAdapter {

    public static List<Service> applyServices(Map<String, String> services) {
        Function<String, Integer> id = s -> {
            Matcher m = Pattern.compile("\\d+").matcher(s);
            return m.find() ? Integer.parseInt(m.group()) : 0;
        };
        return services.entrySet().stream().map(entry -> Service.builder().id(id.apply(entry.getKey())).name(entry.getKey())
                .serviceState(ServiceStatus.valueOf(entry.getValue()).status).build()).collect(Collectors.toList());

    }

    public static List<Arrow> convertMatrixToArrows(int[][] matrix) {
        AtomicInteger from = new AtomicInteger(0);
        AtomicInteger to = new AtomicInteger(1);
        return Stream.of(matrix).map(r -> {
            from.incrementAndGet();
            to.set(1);
            return IntStream.of(r).mapToObj(t -> new Arrow(from.get(), to.getAndIncrement(), t))
                    .filter(a -> a.getTransactionCount() != 0).collect(Collectors.toList());
        }).flatMap(List::stream).collect(Collectors.toList());
    }


}
