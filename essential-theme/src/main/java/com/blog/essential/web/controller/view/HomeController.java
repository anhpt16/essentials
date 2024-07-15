package com.blog.essential.web.controller.view;

import com.blog.essential.web.controller.service.WebEssentialPostControllerService;
import com.blog.essential.web.response.WebLatestPostResponse;
import com.blog.essential.web.response.WebMostViewPostResponse;
import com.blog.essential.web.response.WebMostVotePostResponse;
import com.blog.essential.web.response.WebPostItemResponse;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.RequestParam;
import com.tvd12.ezyhttp.server.core.view.View;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyarticle.web.validator.WebTermValidator;
import org.youngmonkeys.ezyplatform.model.PaginationModel;
import org.youngmonkeys.ezyplatform.web.validator.WebCommonValidator;

import java.util.List;

import static org.youngmonkeys.ezyplatform.constant.CommonConstants.VIEW_VARIABLE_PAGE_TITLE;

@Controller
@AllArgsConstructor
public class HomeController {

    private final WebEssentialPostControllerService essentialPostControllerService;
    private final WebCommonValidator commonValidator;
    private final WebTermValidator termValidator;

    @DoGet("/")
    public View home(
        @RequestParam("author") String authorUuid,
        @RequestParam("term") String termSlug,
        @RequestParam("keyword") String keyword,
        @RequestParam("nextPageToken") String nextPageToken,
        @RequestParam("prevPageToken") String prevPageToken,
        @RequestParam("lastPage") boolean lastPage,
        @RequestParam(value = "limit", defaultValue = "3") int limit
    ) {
        this.commonValidator.validatePageSize(limit);
        this.commonValidator.validateSearchUuid(authorUuid);
        this.commonValidator.validateSearchKeyword(keyword);
        this.termValidator.validateSearchTerm(termSlug);
        WebPostItemResponse mainPost = essentialPostControllerService.getMainPostOrNull();
        List<WebPostItemResponse> extraPosts = essentialPostControllerService.getExtraPosts();
        List<WebMostViewPostResponse> mostViewPosts = essentialPostControllerService
            .getMostViewPosts();
        List<WebMostVotePostResponse> mostVotePosts = essentialPostControllerService
            .getMostVotePosts();
        PaginationModel<WebLatestPostResponse> latestPostPagination = essentialPostControllerService
            .getPostPagination(
                authorUuid,
                termSlug,
                keyword,
                nextPageToken,
                prevPageToken,
                lastPage,
                limit
            );
        return View.builder()
            .template("home") //--> Hàm
            .addVariable("mainPost", mainPost)
            .addVariable("extraPosts", extraPosts)
            .addVariable("mostViewPosts", mostViewPosts)
            .addVariable("mostVotePosts", mostVotePosts)
            .addVariable("latestPostPagination", latestPostPagination)
            .addVariable(VIEW_VARIABLE_PAGE_TITLE, "home")
            .build();
    }
}
