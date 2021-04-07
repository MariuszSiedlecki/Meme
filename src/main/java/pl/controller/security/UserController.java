package pl.controller.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/registration")
    private String registration(Model model){
        model.addAttribute("user", new UserDto());
        model.addAttribute("showErrorExistsUser",Boolean.FALSE);
        return "registration";

    }

    @GetMapping("/login")
    private String loginPage(){
        return "login";

    }

    @PostMapping("/save_user")
    public String saveUser(@ModelAttribute @Valid UserDto userDto,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userDto);
            return "registration";
        }
        try {
            userService.registerUser(userDto);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user",userDto);
            model.addAttribute("showErrorExistsUser",Boolean.TRUE);
            return "registration";
        }
        return "redirect:/login";
    }

    @PostMapping("/addOrDeleteMemeToFavoriteList")
    private String addOrDeleteMemeToFavoriteList(@RequestParam int meme_id, Model model){
        userService.addOrDeleteMemeToFavoriteList(meme_id);
        return "redirect:/favorite";

    }
}
