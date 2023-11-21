package com.blog.blogappapis.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
    private Integer categoryId;

    @NotBlank
    @Size(min = 4, message = "Minimum Size of Category Title should be 4.")
    private String categoryTitle;

    @NotBlank
    @Size(min = 10, message = "Minimum Size of Category Title should be 10.")
    private String categoryDescription;
}
