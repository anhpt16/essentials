package com.blog.essential.web.controller.view;

import com.blog.essential.web.controller.service.WebEssentialPostControllerService;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.view.View;
import org.youngmonkeys.ezyarticle.web.controller.service.WebCommentControllerService;
import org.youngmonkeys.ezyarticle.web.controller.service.WebPostControllerService;
import org.youngmonkeys.ezyarticle.web.controller.view.PostController;
import org.youngmonkeys.ezyarticle.web.service.WebEzyArticleSettingService;
import org.youngmonkeys.ezyarticle.web.service.WebPostService;
import org.youngmonkeys.ezyarticle.web.service.WebTermService;
import org.youngmonkeys.ezyarticle.web.validator.WebTermValidator;
import org.youngmonkeys.ezyplatform.web.service.WebAdminService;
import org.youngmonkeys.ezyplatform.web.service.WebUserService;
import org.youngmonkeys.ezyplatform.web.validator.WebCommonValidator;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebEssentialPostController extends PostController {

    private final WebPostService postService;

    public WebEssentialPostController(
        WebAdminService adminService,
        WebPostService postService,
        WebUserService userService,
        WebTermService termService,
        WebEzyArticleSettingService settingService,
        WebPostControllerService postControllerService,
        WebCommentControllerService commentControllerService,
        WebCommonValidator commonValidator,
        WebTermValidator termValidator) {
        super(
            adminService,
            userService,
            termService,
            settingService,
            postControllerService,
            commentControllerService,
            commonValidator,
            termValidator
        );
        this.postService = postService;
    }

    @Override
    protected void decoratePostDetailsView(
        long postId,
        View.Builder viewBuilder
    ) {
        postService.increaseViewCountByPostId(postId);
        long viewCount = postService
            .getViewCountByPostId(postId)
            .longValue();
        long voteCount = postService
            .getVoteCountByPostId(postId)
            .longValue();
        viewBuilder
            .addVariable("viewCount", viewCount)
            .addVariable("voteCount", voteCount);
    }
}
