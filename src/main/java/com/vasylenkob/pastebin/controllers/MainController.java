package com.vasylenkob.pastebin.controllers;

import com.vasylenkob.pastebin.entities.MetaData;
import com.vasylenkob.pastebin.exceptions.PostDoesNotExistException;
import com.vasylenkob.pastebin.models.PostForm;
import com.vasylenkob.pastebin.models.SavedPost;
import com.vasylenkob.pastebin.models.ShortPostDetails;
import com.vasylenkob.pastebin.services.MetaDataService;
import com.vasylenkob.pastebin.services.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class MainController {

    private final PostsService postsService;
    private final MetaDataService metaDataService;

    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/createpost")
    public String createpost(@ModelAttribute PostForm postForm) {
        return "createpost";
    }

    @PostMapping("/createpost")
    public String createPost(
            @ModelAttribute("postForm") PostForm postForm,
            Authentication authentication
    ) {
        String hash = postsService.savePost(postForm, authentication);
        return "redirect:/post/" + hash;
    }

    @GetMapping("/post/{hash}")
    public String getPost(@PathVariable String hash, Model model){
        SavedPost savedPost = postsService.getPost(hash);
        model.addAttribute("savedPost", savedPost);
        return "viewpost";
    }

    @DeleteMapping("/post/{hash}")
    public String deletePost(@PathVariable String hash) {
        MetaData metaData = metaDataService.findByHash(hash)
                .orElseThrow(() -> new PostDoesNotExistException("Post does not exist"));
        postsService.deletePost(metaData);
        return "redirect:/myposts";
    }

    @GetMapping("/myposts")
    public String getMyPosts(Authentication authentication, Model model){
        List<ShortPostDetails> shortPostDetailsList = postsService.getMyPosts(authentication);
        model.addAttribute("shortPostDetailsList", shortPostDetailsList);
        model.addAttribute("username", authentication.getName());
        return "myposts";
    }
}
