package dmitr2ish.com.github.appBlogs.service;

import dmitr2ish.com.github.appBlogs.entity.Post;

import java.util.List;

public interface PostService {
    Post add(Post post);
    List<Post> getAll();
}
