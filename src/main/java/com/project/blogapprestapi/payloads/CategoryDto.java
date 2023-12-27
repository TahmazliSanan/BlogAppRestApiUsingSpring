package com.project.blogapprestapi.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer id;
    @NotEmpty(message = "Title cannot be empty!")
    private String title;
    @NotEmpty(message = "Description cannot be empty!")
    private String description;
}