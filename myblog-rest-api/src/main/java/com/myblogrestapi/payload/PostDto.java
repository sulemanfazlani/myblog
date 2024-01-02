package com.myblogrestapi.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {

    private Long id;
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;
    @NotEmpty
    @Size(min = 4, message = "Post description should have at least 4 characters")
    private String description;
    @NotEmpty
    @Size(min = 5, message = "Post content should have at least 5 characters")
    private String content;
}
