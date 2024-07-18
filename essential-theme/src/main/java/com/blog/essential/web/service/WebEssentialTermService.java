package com.blog.essential.web.service;

import com.blog.essential.web.converter.WebEssentialResultToModelConverter;
import com.blog.essential.web.repo.WebEssentialTermRepository;
import com.tvd12.ezyfox.util.Next;
import com.tvd12.ezyhttp.server.core.annotation.Service;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyplatform.result.IdResult;

import java.util.List;

import static com.tvd12.ezyfox.io.EzyLists.newArrayList;

@Service
@AllArgsConstructor
public class WebEssentialTermService {

    private final WebEssentialTermRepository essentialTermRepository;
    private final WebEssentialResultToModelConverter essentialResultToModelConverter;

    public long getPostIdByTermId(long termId) {
        IdResult result = essentialTermRepository.findPostIdByTermId(
            termId
        );
        return result != null ? result.getId() : 0L;
    }

    public List<Long> getPostIdsByTermId(long termId, int limit) {
        return newArrayList(
            essentialTermRepository
                .findPostIdsByTermId(termId, Next.limit(limit)),
            IdResult::getId
        );
    }
}
