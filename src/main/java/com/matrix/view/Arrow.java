package com.matrix.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Arrow implements Serializable {

    private int from;
    private int to;
    private Integer transactionCount;

}
