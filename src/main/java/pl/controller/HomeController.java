package pl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.model.Meme;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    List<Meme> memeList = new ArrayList<>();

    @GetMapping("/")
    public String main() {
        return "home";
    }

    @PostMapping("/add")
    public String add(@RequestParam String name,
                      @RequestParam String imageUrl) {
        Meme meme = new Meme(name, imageUrl);
        memeList.add(meme);
        return "home";
    }

    @GetMapping("/showAll")
    public String showAll(ModelMap map) {
        map.put("list", memeList);
        return "result";
    }

}
