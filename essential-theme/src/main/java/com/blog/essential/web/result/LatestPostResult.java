package com.blog.essential.web.result;

import com.tvd12.ezyfox.database.annotation.EzyQueryResult;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EzyQueryResult
public class LatestPostResult {
    private long postId;
    private long authorAdminId;
    private String title;
    private String content;
    private long featuredImageId;
    private long commentCount;
    private long metaNumberValue;
    private LocalDateTime publishedAt;
}
