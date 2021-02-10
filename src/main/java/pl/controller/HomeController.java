package pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.model.Meme;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @GetMapping("/favorite")
    public String favorite(ModelMap map) {
        map.put("list", getFavoriteMemeList());
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

    @PostMapping("/edit")
    public String edit(@RequestParam(value = "meme_id") int meme_id, Model model) {
        model.addAttribute("meme", memeService.getMemeById(meme_id));
        return "add";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(value = "meme_id") int meme_id) {
        memeService.delete(meme_id);
        return "redirect:/";
    }

    @PostMapping("/search")
    public String search(@RequestParam String searchPath, Model model) {
        model.addAttribute("list", getMemeListBySearchPath(searchPath));
        model.addAttribute("searchPath", searchPath);
        return "main";
    }

    public List<Meme> getMemeListBySearchPath(String searchPath) {
        String searchPathTemp = searchPath.toLowerCase();
        return memeService.getMemeList().stream().filter(f -> f.getName().toLowerCase().contains(searchPathTemp) ||
                f.getDescription().toLowerCase().contains(searchPathTemp)).collect(Collectors.toList());
    }

    public List<Meme> sortMemeListById() {
        return memeService.getMemeList().stream().sorted(Comparator.comparingInt(Meme::getId)).collect(Collectors.toList());
    }

    public Set<Meme> getFavoriteMemeList() {
        return memeService.getMemeList().stream().filter(f -> f.isFavorite()).collect(Collectors.toSet());
    }

}
