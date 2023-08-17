package unit;

import core.exceptions.KeyFieldNotFoundException;
import core.utils.JsonReader;
import models.Comments;
import models.Post;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestJsonReader {


    @Test
    public void testValidPost() {
        Post post = JsonReader.getObject(Post.class, 1);
        Assert.assertNotNull(post);
        Assert.assertEquals(post.getUserId(), 1);
        Assert.assertEquals(post.getPostId(), 1);
        Assert.assertEquals(post.getBody(), "Test Body");
        Assert.assertEquals(post.getTitle(), "Test Title");
    }

    @Test
    public void testInValidPost() {
        Post post = JsonReader.getObject(Post.class, 2);
        Assert.assertNull(post);
    }

    @Test
    public void testValidComment() {
        Comments comment = JsonReader.getObject(Comments.class, 1);
        Assert.assertNotNull(comment);
        Assert.assertEquals(comment.getComment(), "Test Comment");
    }

    @Test(expectedExceptions = KeyFieldNotFoundException.class, expectedExceptionsMessageRegExp = "Id field is not found")
    public void testRuntimeError() {
        User user = JsonReader.getObject(User.class, 1);
    }
}
