package com.blog.essential.web.converter;

import com.blog.essential.web.model.LatestPostModel;
import com.blog.essential.web.model.MostViewPostModel;
import com.blog.essential.web.model.MostVotePostModel;
import com.blog.essential.web.result.LatestPostResult;
import com.blog.essential.web.result.MostViewPostResult;
import com.blog.essential.web.result.MostVotePostResult;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import lombok.AllArgsConstructor;

import java.time.ZoneId;

import static org.youngmonkeys.ezyplatform.util.LocalDateTimes.toTimestamp;

@EzySingleton
@AllArgsConstructor
public class WebEssentialResultToModelConverter {

    private final ZoneId zoneId;

    public MostViewPostModel toModel(MostViewPostResult result) {
        if (result == null) {
            return null;
        }
        return MostViewPostModel.builder()
            .postId(result.getPostId())
            .title(result.getTitle())
            .featuredImageId(result.getFeaturedImageId())
            .viewCount(result.getMetaNumberValue())
            .build();
    }

    public MostVotePostModel toModel(MostVotePostResult result) {
        if (result == null) {
            return null;
        }
        return MostVotePostModel.builder()
            .postId(result.getPostId())
            .title(result.getTitle())
            .featuredImageId(result.getFeaturedImageId())
            .build();
    }

    public LatestPostModel toModel(LatestPostResult result) {
        if (result == null) {
            return null;
        }
        return LatestPostModel.builder()
            .postId(result.getPostId())
            .authorAdminId(result.getAuthorAdminId())
            .title(result.getTitle())
            .content(result.getContent())
            .featuredImageId(result.getFeaturedImageId())
            .commentCount(result.getCommentCount())
            .viewCount(result.getMetaNumberValue())
            .publishedAt(toTimestamp(result.getPublishedAt(), zoneId))
            .build();
    }

}
