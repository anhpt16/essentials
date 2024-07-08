package com.blog.essential.web.repo;

import com.blog.essential.web.result.LatestPostResult;
import com.blog.essential.web.result.MostViewPostResult;
import com.blog.essential.web.result.MostVotePostResult;
import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;
import com.tvd12.ezyfox.util.Next;
import org.youngmonkeys.ezyarticle.sdk.entity.Post;
import org.youngmonkeys.ezyarticle.sdk.model.PostModel;

import java.util.List;

@EzyRepository
public interface WebEssentialPostRepository extends EzyDatabaseRepository<Long, Post> {

    @EzyQuery(
        "SELECT e.id, e.title, e.featuredImageId, m.metaNumberValue " +
            "FROM Post e " +
            "LEFT JOIN PostMeta m " +
            "ON e.id = m.postId and m.metaKey = 'views' " +
            "WHERE e.status = 'PUBLISHED' " +
            "AND e.postType = 'POST' " +
            "ORDER BY m.metaNumberValue DESC"
    )
    List<MostViewPostResult> findMostViewPosts(
        Next next
    );

    @EzyQuery(
        "SELECT e.id, e.title, e.featuredImageId " +
            "FROM Post e " +
            "LEFT JOIN PostMeta m " +
            "ON e.id = m.postId and m.metaKey = 'votes' " +
            "WHERE e.status = 'PUBLISHED' " +
            "AND e.postType = 'POST' " +
            "ORDER BY m.metaNumberValue DESC"
    )
    List<MostVotePostResult> findMostVotePosts(Next next);

    @EzyQuery(
        "SELECT e.id, e.authorAdminId, e.title, e.content, e.featuredImageId, e.commentCount, m.metaNumberValue, e.publishedAt " +
            "FROM Post e " +
            "LEFT JOIN PostMeta m " +
            "ON e.id = m.postId and m.metaKey = 'views' " +
            "WHERE e.status = 'PUBLISHED' " +
            "AND e.postType = 'POST' " +
            "ORDER BY e.publishedAt DESC"
    )
    List<LatestPostResult> findLatestPosts();
}
