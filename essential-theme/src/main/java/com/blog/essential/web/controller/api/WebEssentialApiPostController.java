package com.blog.essential.web.controller.api;

import com.blog.essential.web.controller.service.WebEssentialPostControllerService;
import com.blog.essential.web.response.WebLatestPostResponse;
import com.blog.essential.web.response.WebPostVoteResponse;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyarticle.sdk.entity.PostType;
import org.youngmonkeys.ezyarticle.sdk.model.PostModel;
import org.youngmonkeys.ezyarticle.web.service.WebPostService;
import org.youngmonkeys.ezyarticle.web.validator.WebPostValidator;
import org.youngmonkeys.ezyarticle.web.validator.WebTermValidator;
import org.youngmonkeys.ezyplatform.model.PaginationModel;
import org.youngmonkeys.ezyplatform.web.validator.WebCommonValidator;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;

import static org.youngmonkeys.ezyplatform.util.HttpRequests.getLanguage;

@Api
@Controller("/api/v1")
@AllArgsConstructor
public class WebEssentialApiPostController {

    private final WebPostService postService;
    private final WebEssentialPostControllerService essentialPostControllerService;
    private final WebCommonValidator commonValidator;
    private final WebPostValidator postValidator;
    private final WebTermValidator termValidator;

    @DoGet("/posts")
    public PaginationModel<WebLatestPostResponse> postsGet(
        HttpServletRequest request,
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
        return this.essentialPostControllerService
            .getPostPagination(
                authorUuid,
                termSlug,
                keyword,
                nextPageToken,
                prevPageToken,
                lastPage,
                limit
            );
    }

    @DoPost("/posts/{slug}/vote")
    public WebPostVoteResponse postsSlugVoteUpPost(
        HttpServletRequest request,
        @PathVariable String slug
    ) {
        PostModel post = postValidator
            .validatePublishedPostSlugWithTypeAndLanguage(
                slug,
                PostType.POST.toString(),
                getLanguage(request)
            );

        BigInteger totalVote = postService.increaseVoteCount(
            post.getId()
        );
        return new WebPostVoteResponse(totalVote);
    }
}
