package com.blog.essential.web.controller.view;


import com.blog.essential.web.controller.service.WebEssentialPostControllerService;
import com.blog.essential.web.controller.service.WebEssentialTermControllerService;
import com.blog.essential.web.response.WebCategoryTermResponse;
import com.blog.essential.web.response.WebLatestPostResponse;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.RequestParam;
import com.tvd12.ezyhttp.server.core.view.View;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyarticle.web.validator.WebTermValidator;
import org.youngmonkeys.ezyplatform.model.PaginationModel;
import org.youngmonkeys.ezyplatform.web.validator.WebCommonValidator;

import java.util.List;
import java.util.Set;

import static com.tvd12.ezyfox.io.EzyCollections.isEmpty;
import static org.youngmonkeys.ezyplatform.constant.CommonConstants.VIEW_VARIABLE_PAGE_TITLE;

@Controller
@AllArgsConstructor
public class WebEssentialListPostController {
    private final WebEssentialPostControllerService essentialPostControllerService;
    private final WebCommonValidator commonValidator;
    private final WebTermValidator termValidator;
    private final WebEssentialTermControllerService essentialTermControllerService;

    @DoGet("/post-list")
    public View listPost(
        @RequestParam("author") String authorUuid,
        @RequestParam("term") String termSlug,
        @RequestParam("categoryId") Set<Long> categoryIds,
        @RequestParam("keyword") String keyword,
        @RequestParam("nextPageToken") String nextPageToken,
        @RequestParam("prevPageToken") String prevPageToken,
        @RequestParam("lastPage") boolean lastPage,
        @RequestParam(value = "limit", defaultValue = "6") int limit
    ) {
        this.commonValidator.validatePageSize(limit);
        this.commonValidator.validateSearchUuid(authorUuid);
        this.commonValidator.validateSearchKeyword(keyword);
        this.termValidator.validateSearchTerm(termSlug);
        PaginationModel<WebLatestPostResponse> listPostPagination = essentialPostControllerService
            .getPostPagination(
                authorUuid,
                termSlug,
                keyword,
                nextPageToken,
                prevPageToken,
                lastPage,
                isEmpty(categoryIds) ? null : categoryIds,
                limit
            );
        List<WebCategoryTermResponse> listTermCategory = essentialTermControllerService.getNameAndSlugByType();
        return View.builder()
            .template("posts/list")
            .addVariable("listPostPagination", listPostPagination)
            .addVariable("listTermCategory", listTermCategory)
            .addVariable("searchKeyword", keyword)
            .addVariable(VIEW_VARIABLE_PAGE_TITLE, "post")
            .build();
    }

}
