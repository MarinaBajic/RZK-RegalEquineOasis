package com.reo.horseservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntityAlreadyExistsException extends RuntimeException {
    private String message;
    private int idEntity;
}
