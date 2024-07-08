package com.blog.essential.web.model;

import com.tvd12.ezyfox.database.annotation.EzyQueryResult;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class MostViewPostModel {
    private final long postId;
    private final String title;
    private final long featuredImageId;
    private final long viewCount;
}
