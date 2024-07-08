package com.blog.essential.web.controller.decorator;

import com.blog.essential.web.converter.WebEssentialModelToResponseConverter;
import com.blog.essential.web.model.LatestPostModel;
import com.blog.essential.web.model.MostViewPostModel;
import com.blog.essential.web.model.MostVotePostModel;
import com.blog.essential.web.response.WebLatestPostResponse;
import com.blog.essential.web.response.WebMostViewPostResponse;
import com.blog.essential.web.response.WebMostVotePostResponse;
import com.blog.essential.web.response.WebPostItemResponse;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyarticle.sdk.model.PostModel;
import org.youngmonkeys.ezyarticle.web.service.WebPostSlugService;
import org.youngmonkeys.ezyplatform.model.MediaNameModel;
import org.youngmonkeys.ezyplatform.model.UuidNameModel;
import org.youngmonkeys.ezyplatform.web.service.WebAdminService;
import org.youngmonkeys.ezyplatform.web.service.WebMediaService;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.tvd12.ezyfox.io.EzyLists.newArrayList;
import static com.tvd12.ezyfox.io.EzySets.newHashSet;

@EzySingleton
@AllArgsConstructor
public class WebEssentialPostModelDecorator {

    private final WebAdminService adminService;
    private final WebMediaService mediaService;
    private final WebPostSlugService postSlugService;
    private final WebEssentialModelToResponseConverter essentialModelToResponseConverter;

    public WebPostItemResponse decoratePostItem(PostModel model) {
        UuidNameModel author = adminService.getAdminUuidNameById(
            model.getAuthorAdminId()
        );
        MediaNameModel image = mediaService.getMediaNameById(
            model.getFeaturedImageId()
        );
        String slug = postSlugService.getLatestSlugByPostId(
            model.getId()
        );
        return essentialModelToResponseConverter.toPostItemResponse(
            model,
            author,
            image,
            slug
        );
    }

    public List<WebPostItemResponse> decoratePostItems(List<PostModel> models) {
        Set<Long> authorIds = models
            .stream()
            .map(PostModel::getAuthorAdminId)
            .filter(it -> it > 0)
            .collect(Collectors.toSet());
        Map<Long, UuidNameModel> authorByIds = adminService.getAdminUuidNameMapByIds(
            authorIds
        );
        Set<Long> imageIds = models
            .stream()
            .map(PostModel::getFeaturedImageId)
            .filter(it -> it > 0)
            .collect(Collectors.toSet());
        Map<Long, MediaNameModel> imageById = mediaService.getMediaNameMapByIds(
            imageIds
        );
        List<Long> postIds = newArrayList(models, PostModel::getId);
        Map<Long, String> slugByPostId = postSlugService.getLatestSlugMapByPostIds(
            postIds
        );
        return newArrayList(
            models,
            post -> essentialModelToResponseConverter.toPostItemResponse(
                post,
                authorByIds.get(post.getAuthorAdminId()),
                imageById.get(post.getFeaturedImageId()),
                slugByPostId.get(post.getId())
            )
        );
    }

    public List<WebMostViewPostResponse> decorateMostViewPosts(
        List<MostViewPostModel> models
    ) {
        Set<Long> imageIds = models
            .stream()
            .map(MostViewPostModel::getFeaturedImageId)
            .filter(it -> it > 0)
            .collect(Collectors.toSet());
        Map<Long, MediaNameModel> imageById = mediaService.getMediaNameMapByIds(
            imageIds
        );
        List<Long> postIds = newArrayList(models, MostViewPostModel::getPostId);
        Map<Long, String> slugByPostId = postSlugService.getLatestSlugMapByPostIds(
            postIds
        );
        return newArrayList(
            models,
            post -> essentialModelToResponseConverter.toMostViewPostResponse(
                post,
                imageById.getOrDefault(
                    post.getFeaturedImageId(),
                    MediaNameModel.builder().build()
                ),
                slugByPostId.get(post.getPostId())
            )
        );
    }

    public List<WebMostVotePostResponse> decorateMostVotePosts(
        List<MostVotePostModel> models
    ) {
        // Lấy ra hình ảnh - id
        Set<Long> imageIds = models
            .stream()
            .map(MostVotePostModel::getFeaturedImageId)
            .filter(it -> it > 0)
            .collect(Collectors.toSet());
        Map<Long, MediaNameModel> imageById = mediaService.getMediaNameMapByIds(imageIds);
        // Lấy ra slug - id
        List<Long> postIds = newArrayList(models, MostVotePostModel::getPostId);
        Map<Long, String> slugByPostId = postSlugService.getLatestSlugMapByPostIds(postIds);
        // Trả về danh sách các response
        return newArrayList(
            models,
            post -> essentialModelToResponseConverter.toMostVotePostResponse(
                post,
                imageById.getOrDefault(
                    post.getFeaturedImageId(),
                    MediaNameModel.builder().build()
                ),
                slugByPostId.get(post.getPostId())
            )
        );
    }

    public List<WebLatestPostResponse> decorateLatestPosts(
        List<LatestPostModel> models
    ) {
        // Lấy ra id - slug
        List<Long> postIds = newArrayList(models, LatestPostModel::getPostId);
        Map<Long, String> slugByPostId = postSlugService.getLatestSlugMapByPostIds(postIds);
        // Lấy author
        Set<Long> authorAdminIds = newHashSet(models, LatestPostModel::getAuthorAdminId);
        Map<Long, UuidNameModel> nameByAuthor = adminService.getAdminUuidNameMapByIds(authorAdminIds);
        // Lấy image
        Set<Long> imageIds = models
            .stream()
            .map(LatestPostModel::getFeaturedImageId)
            .filter(it -> it > 0)
            .collect(Collectors.toSet());
        Map<Long, MediaNameModel> imageById = mediaService.getMediaNameMapByIds(imageIds);
        // Converter
        return newArrayList(
            models,
            post -> essentialModelToResponseConverter.toLatestPostResponse(
                post,
                nameByAuthor.get(post.getAuthorAdminId()),
                imageById.getOrDefault(
                    post.getFeaturedImageId(),
                    MediaNameModel.builder().build()
                ),
                slugByPostId.get(post.getPostId()),
                post.getViewCount()
            )
        );
    }
}
