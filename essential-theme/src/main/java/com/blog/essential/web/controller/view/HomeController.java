package com.blog.essential.web.controller.view;

import com.blog.essential.web.controller.service.WebEssentialPostControllerService;
import com.blog.essential.web.response.WebMostViewPostResponse;
import com.blog.essential.web.response.WebPostItemResponse;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.view.View;
import lombok.AllArgsConstructor;

import java.util.List;

import static org.youngmonkeys.ezyplatform.constant.CommonConstants.VIEW_VARIABLE_PAGE_TITLE;

@Controller
@AllArgsConstructor
public class HomeController {

    private final WebEssentialPostControllerService essentialPostControllerService;

    @DoGet("/")
    public View home() {
        WebPostItemResponse mainPost = essentialPostControllerService.getMainPostOrNull();
        List<WebPostItemResponse> extraPosts = essentialPostControllerService.getExtraPosts();
        List<WebMostViewPostResponse> mostViewPosts = essentialPostControllerService
            .getMostViewPosts();
        return View.builder()
            .template("home") //--> Hàm
            .addVariable("mainPost", mainPost)
            .addVariable("extraPosts", extraPosts)
            .addVariable("mostViewPosts", mostViewPosts)
            .addVariable(VIEW_VARIABLE_PAGE_TITLE, "home")
            .build();
    }
}
