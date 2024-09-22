package com.example.megachat.annotation;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Parameter(in = ParameterIn.QUERY,
        description = "Номер возвращаемой страницы",
        name = "page",
        content = @Content(schema = @Schema(type = "integer", defaultValue = "0")))
@Parameter(in = ParameterIn.QUERY,
        description = "Размер возврашаемой страницы",
        name = "size",
        content = @Content(schema = @Schema(type = "integer", defaultValue = "10")))
@Parameter(in = ParameterIn.QUERY,
        description = "Критерии сортировки в формате: колонка(,asc|desc)",
        name = "sort",
        content = @Content(array = @ArraySchema(schema = @Schema(type = "string"))))
public @interface ApiPageable {
}
