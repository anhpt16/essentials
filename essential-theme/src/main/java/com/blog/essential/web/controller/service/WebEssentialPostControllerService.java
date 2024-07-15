package com.blog.essential.web.controller.service;

import com.blog.essential.web.controller.decorator.WebEssentialPostModelDecorator;
import com.blog.essential.web.model.MostViewPostModel;
import com.blog.essential.web.model.MostVotePostModel;
import com.blog.essential.web.response.WebLatestPostResponse;
import com.blog.essential.web.response.WebMostViewPostResponse;
import com.blog.essential.web.response.WebMostVotePostResponse;
import com.blog.essential.web.response.WebPostItemResponse;
import com.blog.essential.web.service.WebEssentialPostService;
import com.blog.essential.web.service.WebEssentialTermService;
import com.tvd12.ezyhttp.server.core.annotation.Service;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyarticle.sdk.entity.PostStatus;
import org.youngmonkeys.ezyarticle.sdk.entity.PostType;
import org.youngmonkeys.ezyarticle.sdk.model.PostModel;
import org.youngmonkeys.ezyarticle.sdk.model.TermModel;
import org.youngmonkeys.ezyarticle.sdk.pagination.PostFilter;
import org.youngmonkeys.ezyarticle.web.pagination.WebPostFilterFactory;
import org.youngmonkeys.ezyarticle.web.service.WebPaginationPostService;
import org.youngmonkeys.ezyarticle.web.service.WebPostService;
import org.youngmonkeys.ezyarticle.web.service.WebTermService;
import org.youngmonkeys.ezyplatform.model.AdminModel;
import org.youngmonkeys.ezyplatform.model.PaginationModel;
import org.youngmonkeys.ezyplatform.web.service.WebAdminService;

import java.util.Collections;
import java.util.List;

import static com.blog.essential.constant.EssentialConstants.*;
import static com.tvd12.ezyfox.io.EzyStrings.isNotBlank;
import static org.youngmonkeys.ezyplatform.pagination.PaginationModelFetchers.getPaginationModel;

@Service
@AllArgsConstructor
public class WebEssentialPostControllerService {

    private final WebPostFilterFactory postFilterFactory;
    private final WebAdminService adminService;
    private final WebEssentialPostService essentialPostService;
    private final WebEssentialTermService essentialTermService;
    private final WebPostService postService;
    private final WebTermService termService;
    private final WebPaginationPostService paginationPostService;
    private final WebEssentialPostModelDecorator essentialPostModelDecorator;

    public WebPostItemResponse getMainPostOrNull() {
        TermModel mainTerm = termService.getTermByTypeAndName(
            TERM_HIGHLIGHT_TYPE,
            TERM_NAME_MAIN
        );
        if (mainTerm == null) {
            return null;
        }
        long postId = essentialTermService.getPostIdByTermId(
            mainTerm.getId()
        );
        if (postId <= 0) {
            return null;
        }
        PostModel post = postService.getPublishedPostById(postId);
        if (post == null) {
            return null;
        }
        return essentialPostModelDecorator.decoratePostItem(post);
    }

    public List<WebPostItemResponse> getExtraPosts() {
        TermModel extraTerm = termService.getTermByTypeAndName(
            TERM_EXTRA_TYPE,
            TERM_NAME_EXTRA
        );
        if (extraTerm == null) {
            return Collections.emptyList();
        }
        List<Long> postIds = essentialTermService.getPostIdsByTermId(
            extraTerm.getId(),
            NUMBER_OF_EXTRA_POST
        );
        List<PostModel> posts = essentialPostService.getPostsByIds(
            postIds
        );
        return essentialPostModelDecorator.decoratePostItems(posts);
    }

    public List<WebMostViewPostResponse> getMostViewPosts() {
        List<MostViewPostModel> posts = essentialPostService.getMostViewPosts(
            NUMBER_OF_MOST_VIEW_POSTS
        );
        return essentialPostModelDecorator.decorateMostViewPosts(posts);
    }

    public List<WebMostViewPostResponse> getFooterMostViewPosts() {
        List<MostViewPostModel> posts = essentialPostService.getMostViewPosts(
            NUMBER_OF_MOST_VIEW_POSTS_FOOTER
        );
        return essentialPostModelDecorator.decorateMostViewPosts(posts);
    }

    public List<WebMostVotePostResponse> getMostVotePosts() {
        List<MostVotePostModel> posts = essentialPostService
            .getMostVotePosts(NUMBER_OF_MOST_VOTE_POSTS);
        return essentialPostModelDecorator.decorateMostVotePosts(posts);
    }

    public List<WebMostVotePostResponse> getFooterMostVotePosts() {
        List<MostVotePostModel> posts = essentialPostService
            .getMostVotePosts(NUMBER_OF_MOST_VOTE_POSTS_FOOTER);
        return essentialPostModelDecorator.decorateMostVotePosts(posts);
    }

    public PaginationModel<WebLatestPostResponse> getPostPagination(
        String authorUuid,
        String termSlug,
        String keyword,
        String nextPageToken,
        String prevPageToken,
        boolean lastPage,
        int limit
    ) {
        Long authorAdminId = null;
        if (isNotBlank(authorUuid)) {
            AdminModel authorAdmin = adminService.getAdminByUuid(authorUuid);
            if (authorAdmin != null) {
                authorAdminId = authorAdmin.getId();
            }
        }
        return getPostPagination(
            postFilterFactory.newDefaultPostFilterBuilder(keyword)
                .authorAdminId(authorAdminId)
                .termSlug(termSlug)
                .postStatus(PostStatus.PUBLISHED.toString())
                .postType(PostType.POST.toString())
                .build(),
            nextPageToken,
            prevPageToken,
            lastPage,
            limit
        );
    }

    public PaginationModel<WebLatestPostResponse> getPostPagination(
        PostFilter postFilter,
        String nextPageToken,
        String prevPageToken,
        boolean lastPage,
        int limit
    ) {
        PaginationModel<PostModel> pagination = getPaginationModel(
            paginationPostService,
            postFilter,
            nextPageToken,
            prevPageToken,
            lastPage, limit
        );
        return essentialPostModelDecorator.decoratePostPagination(pagination);
    }
}
