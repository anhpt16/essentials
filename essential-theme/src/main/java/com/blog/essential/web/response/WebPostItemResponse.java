package com.blog.essential.web.response;

import lombok.Builder;
import lombok.Getter;
import org.youngmonkeys.ezyplatform.model.MediaNameModel;
import org.youngmonkeys.ezyplatform.model.UuidNameModel;

@Getter
@Builder
public class WebPostItemResponse {
    private final MediaNameModel image;
    private final UuidNameModel author;
    private final String title;
    private final String content;
    private final String slug;
    private final long publishedAt;
}
// repo --> service --> controller
// convert:lớp kết nối giũa các tầng