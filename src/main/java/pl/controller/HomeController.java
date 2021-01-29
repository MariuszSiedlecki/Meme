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

    public List<Meme> memeList;
    private int countIdFromMeme;

    public HomeController() {
        this.memeList = MemeUtils.memeListData();
        this.countIdFromMeme = memeList.size();
    }

    @GetMapping("/")
    public String main(ModelMap map) {
        map.put("list", sortMemeListById());
        return "main";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Meme meme) {

        if (meme.getId() == 0) {
            countIdFromMeme++;
            meme.setId(countIdFromMeme);
        } else {
            memeList.remove(getMemeById(meme.getId()));
        }
        memeList.add(meme);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String add(Model model) {
        Meme newMeme = new Meme();
        model.addAttribute("meme", newMeme);
        return "add";
    }

}
