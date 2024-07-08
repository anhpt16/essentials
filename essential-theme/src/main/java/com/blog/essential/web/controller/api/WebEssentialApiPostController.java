package com.blog.essential.web.controller.api;

import com.blog.essential.web.response.WebPostVoteResponse;
import com.tvd12.ezyhttp.server.core.annotation.Api;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.PathVariable;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyarticle.sdk.entity.PostType;
import org.youngmonkeys.ezyarticle.sdk.model.PostModel;
import org.youngmonkeys.ezyarticle.web.service.WebPostService;
import org.youngmonkeys.ezyarticle.web.validator.WebPostValidator;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;

import static org.youngmonkeys.ezyplatform.util.HttpRequests.getLanguage;

@Api
@Controller("/api/v1")
@AllArgsConstructor
public class WebEssentialApiPostController {

    private final WebPostService postService;
    private final WebPostValidator postValidator;

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
