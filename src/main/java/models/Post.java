package models;


import core.annotations.JsonId;
import lombok.Data;

@Data
public class Post {

    @JsonId
    private int userId;
    private int postId;
    private String title;
    private String body;
}
