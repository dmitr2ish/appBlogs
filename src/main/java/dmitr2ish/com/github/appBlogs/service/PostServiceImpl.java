package dmitr2ish.com.github.appBlogs.service;

import dmitr2ish.com.github.appBlogs.entity.Post;
import dmitr2ish.com.github.appBlogs.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService{
    final private PostRepository repository;

    @Autowired
    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public Post add(Post post) {
        return repository.add(new Post(post.getContent()));
    }

    @Override
    public List<Post> getAll() {
        return repository.getAll();
    }
}
