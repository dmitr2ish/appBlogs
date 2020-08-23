package dmitr2ish.com.github.appBlogs.controller;

import dmitr2ish.com.github.appBlogs.entity.Post;
import dmitr2ish.com.github.appBlogs.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Random;

@Controller
public class ViewController {
    final private PostService service;

    @Autowired
    public ViewController(PostService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ModelAndView getMainPage() {
        return new ModelAndView("main");
    }

    @GetMapping("/init")
    public ModelAndView init() {
        for (int i = 0; i < 10; i++) {
            service.add(new Post(generateString()));
        }
        return new ModelAndView("main")
                .addObject("postList", service.getAll());
    }

    public String generateString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
