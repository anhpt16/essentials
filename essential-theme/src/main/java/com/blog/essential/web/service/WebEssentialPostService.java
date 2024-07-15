package com.blog.essential.web.service;

import com.blog.essential.web.converter.WebEssentialResultToModelConverter;
import com.blog.essential.web.model.LatestPostModel;
import com.blog.essential.web.model.MostViewPostModel;
import com.blog.essential.web.model.MostVotePostModel;
import com.blog.essential.web.repo.WebEssentialPostRepository;
import com.tvd12.ezyfox.util.Next;
import com.tvd12.ezyhttp.server.core.annotation.Service;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyarticle.sdk.model.PostModel;
import org.youngmonkeys.ezyarticle.web.converter.WebEzyArticleEntityToModelConverter;
import org.youngmonkeys.ezyarticle.web.repo.WebPostRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.tvd12.ezyfox.io.EzyLists.newArrayList;

@Service
@AllArgsConstructor
public class WebEssentialPostService {

    private final WebEssentialPostRepository essentialPostRepository;
    private final WebPostRepository postRepository;
    private final WebEssentialResultToModelConverter essentialResultToModelConverter;
    private final WebEzyArticleEntityToModelConverter ezyArticleEntityToModelConverter;

    public List<PostModel> getPostsByIds(Collection<Long> ids) {
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        return newArrayList(
            postRepository.findListByIds(ids),
            ezyArticleEntityToModelConverter::toModel
        );
    }

    public List<MostViewPostModel> getMostViewPosts(int limit) {
        return newArrayList(
            essentialPostRepository.findMostViewPosts(
                Next.limit(limit)
            ),
            essentialResultToModelConverter::toModel
        );
    }

    public List<MostVotePostModel> getMostVotePosts(int limit) {
        return newArrayList(
            essentialPostRepository.findMostVotePosts(Next.limit(limit)),
            essentialResultToModelConverter::toModel
        );
    }

    public List<LatestPostModel> getLatestPosts() {
        return newArrayList(
            essentialPostRepository.findLatestPosts(),
            essentialResultToModelConverter::toModel
        );
    }
}
