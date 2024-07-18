package com.blog.essential.web.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WebCategoryTermResponse {
    private final String name;
    private final String slug;
    private final long termId;
}
