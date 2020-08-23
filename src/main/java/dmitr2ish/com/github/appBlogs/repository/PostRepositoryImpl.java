package dmitr2ish.com.github.appBlogs.repository;

import dmitr2ish.com.github.appBlogs.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository {

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
    public List<Post> getFirstFive() {
        Long idMin = (Long) entityManager.createQuery("select min(c.id) from Post c").getSingleResult();
        if (idMin == null) {
            return null;
        }
        Long firstFiveId = idMin + 4L;
        Long maxIdTable = (Long) entityManager.createQuery("select max(c.id) from Post c").getSingleResult();
        //if the table has fewer than five elements the maximum id of the first five is the maximum ID of the table
        if (firstFiveId > maxIdTable) {
            firstFiveId = maxIdTable;
        }
        return entityManager.createQuery("select c from Post c where c.id between :idMin and :firstFiveId")
                .setParameter("idMin", idMin)
                .setParameter("firstFiveId", firstFiveId)
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> getNextFive(Long idMin) {
        Long maxId = idMin + 5L;
        Long maxIdTable = (Long) entityManager.createQuery("select max(c.id) from Post c").getSingleResult();
        //if there are not enough elements in the table, the maximum id is equal to the maximum in the table
        if (maxId > maxIdTable) {
            maxId = maxIdTable;
        }
        return entityManager.createQuery("select c from Post c where c.id between :idmin and :idmax")
                .setParameter("idmin", idMin + 1L)
                .setParameter("idmax", maxId)
                .getResultList();
    }

    @Override
    public boolean deleteAll() {
        int a = entityManager.createQuery("delete from Post c").executeUpdate();
        return a != 0;
    }
}
