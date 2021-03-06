package com.example.demo.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonDeserialize
public class Pageable {
    private Sort sort;
    private int offset;
    private int pageSize;
    private int pageNumber;
    private boolean paged;
    private boolean unpaged;
}
