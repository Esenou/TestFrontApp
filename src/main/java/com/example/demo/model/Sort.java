package com.example.demo.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonDeserialize
public class Sort {
    private boolean sorted;
    private boolean unsorted;
    private boolean empty;

}
