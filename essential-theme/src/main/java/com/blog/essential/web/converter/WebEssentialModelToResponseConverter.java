package com.blog.essential.web.converter;

import com.blog.essential.web.model.MostViewPostModel;
import com.blog.essential.web.model.MostVotePostModel;
import com.blog.essential.web.response.WebLatestPostResponse;
import com.blog.essential.web.response.WebMostViewPostResponse;
import com.blog.essential.web.response.WebMostVotePostResponse;
import com.blog.essential.web.response.WebPostItemResponse;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import org.youngmonkeys.ezyarticle.sdk.model.PostModel;
import org.youngmonkeys.ezyplatform.model.MediaNameModel;
import org.youngmonkeys.ezyplatform.model.UuidNameModel;

import java.math.BigInteger;

@EzySingleton
public class WebEssentialModelToResponseConverter {

    public WebPostItemResponse toPostItemResponse(
        PostModel model,
        UuidNameModel author,
        MediaNameModel image,
        String slug
    ) {
        return WebPostItemResponse.builder()
            .image(image)
            .title(model.getTitle())
            .author(author)
            .content(model.getContent())
            .slug(slug)
            .publishedAt(model.getPublishedAt())
            .build();
    }

    public WebMostViewPostResponse toMostViewPostResponse(
        MostViewPostModel model,
        MediaNameModel image,
        String slug
    ) {
        return WebMostViewPostResponse.builder()
            .image(image)
            .title(model.getTitle())
            .slug(slug)
            .viewCount(model.getViewCount())
            .build();
    }

    public WebMostVotePostResponse toMostVotePostResponse(
        MostVotePostModel model,
        MediaNameModel image,
        String slug
    ) {
        return WebMostVotePostResponse.builder()
            .title(model.getTitle())
            .slug(slug)
            .image(image)
            .build();
    }

    public WebLatestPostResponse toLatestPostResponse(
        PostModel model,
        UuidNameModel author,
        MediaNameModel image,
        String slug,
        BigInteger viewCount
    ) {
        return WebLatestPostResponse.builder()
            .slug(slug)
            .image(image)
            .author(author)
            .publishedAt(model.getPublishedAt())
            .title(model.getTitle())
            .content(model.getContent())
            .commentCount(model.getCommentCount())
            .viewCount(viewCount)
            .build();
    }
}
