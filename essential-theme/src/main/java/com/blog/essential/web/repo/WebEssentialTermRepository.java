package com.blog.essential.web.repo;

import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;
import com.tvd12.ezyfox.util.Next;
import org.youngmonkeys.ezyarticle.sdk.entity.PostTerm;
import org.youngmonkeys.ezyarticle.sdk.entity.PostTermId;
import org.youngmonkeys.ezyplatform.result.IdResult;

import java.util.List;

@EzyRepository
public interface WebEssentialTermRepository
        extends EzyDatabaseRepository<PostTermId, PostTerm> {

    @EzyQuery(
        "SELECT e.postId FROM PostTerm e WHERE e.termId = ?0"
    )
    IdResult findPostIdByTermId(long termId);

    @EzyQuery(
            "SELECT e.postId FROM PostTerm e WHERE e.termId = ?0"
    )
    List<IdResult> findPostIdsByTermId(long termId, Next next);

}
