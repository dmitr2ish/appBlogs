package dmitr2ish.com.github.appBlogs.repository;

import dmitr2ish.com.github.appBlogs.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository{

    final private EntityManager entityManager;

    @Autowired
    public PostRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Post add(Post post) {
        entityManager.persist(post);
        entityManager.flush();
        return post;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> getAll() {
        return entityManager.createQuery("select c from Post c")
                .getResultList();
    }
}
