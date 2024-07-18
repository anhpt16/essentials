package com.blog.essential.web.controller.service;


import com.blog.essential.constant.EssentialConstants;
import com.blog.essential.web.converter.WebEssentialModelToResponseConverter;
import com.blog.essential.web.response.WebCategoryTermResponse;
import com.tvd12.ezyhttp.server.core.annotation.Service;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyarticle.sdk.model.TermModel;
import org.youngmonkeys.ezyarticle.web.service.WebTermService;
import static com.tvd12.ezyfox.io.EzyLists.newArrayList;
import java.util.List;

import static com.tvd12.ezyfox.io.EzyLists.newArrayList;

@Service
@AllArgsConstructor
public class WebEssentialTermControllerService {
    private final WebTermService termService;
    private final WebEssentialModelToResponseConverter essentialModelToResponseConverter;

    public List<WebCategoryTermResponse> getNameAndSlugByType() {
        List<TermModel> terms = termService.getTermsByType(EssentialConstants.TERM_CATEGORY_TYPE);
        if (terms == null) {
            return null;
        }
        return newArrayList(
            terms,
            essentialModelToResponseConverter::toCategoryTermResponse
        );
    }
}
