package com.blog.essential.web.response;

import lombok.Builder;
import lombok.Getter;
import org.youngmonkeys.ezyplatform.model.MediaNameModel;

@Getter
@Builder
public class WebMostViewPostResponse {
    private final MediaNameModel image;
    private final String title;
    private final String slug;
    private final long viewCount;
}
