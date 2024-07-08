package com.blog.essential.web.controller.view;

import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;
import com.tvd12.ezyhttp.server.core.annotation.PathVariable;
import com.tvd12.ezyhttp.server.core.view.View;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyarticle.sdk.entity.TermType;
import org.youngmonkeys.ezyarticle.sdk.model.TermModel;
import org.youngmonkeys.ezyarticle.web.service.WebTermService;

import static org.youngmonkeys.ezyplatform.constant.CommonConstants.VIEW_VARIABLE_PAGE_TITLE;

@Controller
@AllArgsConstructor
public class WebEssentialCategoryController {

    private final WebTermService termService;

    @DoGet("/categories/{slug}")
    public View categoriesGet(
        @PathVariable String categorySlug
    ) {
        TermModel category = termService.getTermByTypeAndSlug(
            TermType.CATEGORY.toString(),
            categorySlug
        );
        return View.builder()
            .template("categories/details")
            .addVariable("category", category)
            .addVariable(VIEW_VARIABLE_PAGE_TITLE, category.getName())
            .build();
    }
}
