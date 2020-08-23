package dmitr2ish.com.github.appBlogs.repository;

import dmitr2ish.com.github.appBlogs.entity.Post;

import java.util.List;

public interface PostRepository {
    Post add(Post post);
    List<Post> getAll();
}
