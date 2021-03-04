package com.matrix.utils;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.out;

@Service
@RequestScope
public class ServiceDataCollector {

    public  int[][] collectServicesMatrix(int matrixSize) {
        Random r = new Random();
        int matrix[][] = IntStream.range(0, matrixSize).mapToObj(i -> IntStream.generate(()
                -> r.nextInt(2) * r.nextInt(2) * r.nextInt(50)).limit(matrixSize).toArray()).toArray(int[][]::new);
        Stream.of(matrix).forEach(e -> out.println(Arrays.toString(e)));
        out.println("\n------------------------------\n");
        return matrix;
    }

    public  Map<String, String> collectServicesStates(int matrixSize) {
        IntFunction<String> status = i -> new Random().nextInt(i) > 2 ? "STOPPED" : "RUNNING";
        return IntStream.range(1, matrixSize + 1).boxed().collect(Collectors.toMap(i -> "Service " + i, i -> status.apply(i)));
    }
}
