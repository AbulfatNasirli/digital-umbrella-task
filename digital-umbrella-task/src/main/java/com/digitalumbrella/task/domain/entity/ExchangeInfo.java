package com.digitalumbrella.task.domain.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ExchangeInfo  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String currValue;
    private String nominal;
    private String valType;
    private String valCode;
    private String date;

    public ExchangeInfo() {

    }
}
