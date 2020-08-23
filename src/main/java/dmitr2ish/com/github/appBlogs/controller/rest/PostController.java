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

    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> getFirstFive() {
        List<Post> list = service.getFirstFive();
        return list != null
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/next/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> getNextFive(@PathVariable(value = "id") Long id) {
        List<Post> list = service.getNextFive(id);
        return !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteAll() {
        return (service.deleteAll())
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
