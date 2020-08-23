package dmitr2ish.com.github.appBlogs.controller.rest;

import dmitr2ish.com.github.appBlogs.entity.Post;
import dmitr2ish.com.github.appBlogs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post/")
public class PostController {
    final private PostService service;

    @Autowired
    public PostController(PostService service) {
        this.service = service;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addHistory(@RequestBody Post post) {
        return (service.add(post).getId() != null)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> getAllPost() {
        List<Post> list = service.getAll();
        return !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
