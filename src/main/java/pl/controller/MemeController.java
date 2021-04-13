package pl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.controller.security.UserDto;
import pl.controller.security.UserService;
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
@RequiredArgsConstructor
public class MemeController {

    private final MemeService memeService;
    private final UserService userService;

//    @Autowired
//    public MemeController(MemeService memeService, UserService userService) {
//        this.memeService = memeService;
//        this.userService = userService;
//       // memeService.save(MemeUtils.memeListData());
//    }

    @GetMapping("/")
    public String main(ModelMap map) {
        map.put("list", sortMemeListById());
        setFavoriteMemeListByUser();
        return "main";
    }

    @GetMapping("/favorite")
    public String favorite(ModelMap map) {
        map.put("list", getFavoriteMemeList());
        return "main";
    }

    @PostMapping("/save")
    public String save(@RequestParam MultipartFile file, @ModelAttribute @Valid Meme meme, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("meme", meme);
            return "add";
        }
        if (meme.getImage() == null || meme.getImage().isEmpty()) {

            try {
                InputStream inputStream;
                if (meme.getImageUrl() != null && !meme.getImageUrl().isEmpty()) {
                    inputStream = new URL(meme.getImageUrl()).openStream();
                } else if (file != null) {
                    inputStream = file.getInputStream();
                } else {
                    model.addAttribute("meme", meme);
                    return "add";
                }
                meme.setImage(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("meme", meme);
                return "add";
            }
        }

        meme.setUserDtoOwner(userService.getCurrentUser());

        memeService.save(meme);
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

    @GetMapping("/yourmeme")
    public String yourMemList(ModelMap model) {
        model.addAttribute("list", userService.getCurrentUser().getMemeList());
        return "yourmeme";
    }

    public List<Meme> getMemeListBySearchPath(String searchPath) {
        String searchPathTemp = searchPath.toLowerCase();
        return memeService.getMemeList().stream().filter(f -> f.getName().toLowerCase().contains(searchPathTemp) ||
                f.getDescription().toLowerCase().contains(searchPathTemp)).collect(Collectors.toList());
    }

    public List<Meme> sortMemeListById() {
        return memeService.getMemeList().stream().sorted(Comparator.comparingInt(Meme::getId)).collect(Collectors.toList());
    }

    public List<Meme> getFavoriteMemeList() {
        setFavoriteMemeListByUser();
        return memeService.getMemeList().stream().filter(f -> f.isFavorite()).collect(Collectors.toList());
    }

    private void setFavoriteMemeListByUser() {
        UserDto currentUser = userService.getCurrentUser();
        if (currentUser != null) {
            Set<Integer> favoriteMemeListByUser = currentUser.getFavoriteMemeIdList();
            memeService.getMemeList().stream().filter(f -> favoriteMemeListByUser.contains(f.getId()))
                    .peek(m -> m.setFavorite(true)).collect(Collectors.toList());
        }
    }
//    private UserDto getCurrentUser(){
//        AbstractAuthenticationToken authenticationToken = (AbstractAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//        if (!authenticationToken.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))){
//        return (UserDto) authenticationToken.getPrincipal();
//
//        }
//        return null;
//    }


}
