package com.blog.essential.web.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LatestPostModel {
    private final long postId;
    private final long authorAdminId;
    private final String title;
    private final String content;
    private final long featuredImageId;
    private final long commentCount;
    private final long viewCount;
    private final long publishedAt;
}
