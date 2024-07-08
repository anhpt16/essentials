package com.blog.essential.web.view;

import com.blog.essential.web.controller.service.WebEssentialPostControllerService;
import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.server.core.view.View;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyarticle.web.service.WebPostService;
import org.youngmonkeys.ezyarticle.web.service.WebTermService;
import org.youngmonkeys.ezyplatform.web.view.WebViewDecorator;

import javax.servlet.http.HttpServletRequest;

@EzySingleton
@AllArgsConstructor
public class WebEssentialViewDecorator extends WebViewDecorator {

    private final WebTermService termService;
    private final WebEssentialPostControllerService postControllerService;

    @Override
    public void decorate(HttpServletRequest request, View view) {
        super.decorate(request, view);
        decorateMainMenu(view);
        decorateFooter(view);
    }

    private void decorateMainMenu(View view) {
        view.setVariable(
            "categories",
            termService.getTermsInCategoryType()
        );
    }

    private void decorateFooter(View view) {
        view.setVariable("trendings", postControllerService.getFooterMostViewPosts());
        view.setVariable("featureds", postControllerService.getFooterMostVotePosts());
    }

}
// Lớp này sẽ được chạy sau khi View được tạo ra nó sẽ được framework gọi ở bên trong trước khi gen html từ template
