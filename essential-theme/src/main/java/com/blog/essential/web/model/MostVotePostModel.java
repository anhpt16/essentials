package com.blog.essential.web.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MostVotePostModel {
    private final long postId;
    private final String title;
    private final long featuredImageId;
}
