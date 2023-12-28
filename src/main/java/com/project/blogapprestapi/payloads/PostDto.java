package com.project.blogapprestapi.payloads;

import com.project.blogapprestapi.entities.Category;
import com.project.blogapprestapi.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDto {
    private Integer id;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private User user;
    private Category category;
}