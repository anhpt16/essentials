package com.blog.essential.web.result;

import com.tvd12.ezyfox.database.annotation.EzyQueryResult;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EzyQueryResult
public class MostVotePostResult {
    private long postId;
    private String title;
    private long featuredImageId;
}
