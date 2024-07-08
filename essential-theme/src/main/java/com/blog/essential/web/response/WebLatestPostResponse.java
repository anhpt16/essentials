package com.blog.essential.web.response;

import lombok.Builder;
import lombok.Getter;
import org.youngmonkeys.ezyplatform.model.MediaNameModel;
import org.youngmonkeys.ezyplatform.model.UuidNameModel;

@Getter
@Builder
public class WebLatestPostResponse {
    private final MediaNameModel image;
    private final UuidNameModel author;
    private final String title;
    private final String content;
    private final long publishedAt;
    private final long commentCount;
    private final long viewCount;
    private final String slug;
}
