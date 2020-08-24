package dmitr2ish.com.github.appBlogs;

import dmitr2ish.com.github.appBlogs.entity.Post;
import dmitr2ish.com.github.appBlogs.repository.PostRepositoryImpl;
import dmitr2ish.com.github.appBlogs.service.PostServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.TransactionRequiredException;
import java.util.ArrayList;
import java.util.List;

/**
 * JUnit-testing methods's class PostServiceImpl
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest extends Mockito {

    @Autowired
    private PostServiceImpl service;

    @MockBean
    private PostRepositoryImpl repository;

    /**
     * checking to get list of five post
     */
    @Test
    public void getFirstFiveList() {
        //checking to return type
        Mockito.doReturn(new ArrayList<Post>())
                .when(repository)
                .getFirstFive();
        //checking method to return list of posts
        List<Post> posts = new ArrayList<>();
        Post somePost_1 = new Post();
        Post somePost_2 = new Post();
        posts.add(somePost_1);
        posts.add(somePost_2);
        Mockito.when(service.getFirstFive()).thenReturn(posts);

        //checking to null
        Assert.assertNotNull("checking to null", service.getFirstFive());
        //checking repository
        Mockito.verify(repository, Mockito.times(1)).getFirstFive();
    }

    /**
     * checking to throw exception
     */
    @Test(expected = TransactionRequiredException.class)
    public void failGetListOfFirstFive() {
        Mockito.doThrow(new TransactionRequiredException()).
                when(repository)
                .getFirstFive();
        Assert.assertNotNull("checking to null", service.getFirstFive());
    }
}
