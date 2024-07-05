package com.blog.essential.web.view;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyhttp.server.core.view.View;
import lombok.AllArgsConstructor;
import org.youngmonkeys.ezyarticle.web.service.WebTermService;
import org.youngmonkeys.ezyplatform.web.view.WebViewDecorator;

import javax.servlet.http.HttpServletRequest;

@EzySingleton
@AllArgsConstructor
public class WebEssentialViewDecorator extends WebViewDecorator {

    private final WebTermService termService;

    @Override
    public void decorate(HttpServletRequest request, View view) {
        super.decorate(request, view);
        decorateMainMenu(view);
    }

    private void decorateMainMenu(View view) {
        view.setVariable(
            "categories",
            termService.getTermsInCategoryType()
        );
    }
}
// Lớp này sẽ được chạy sau khi View được tạo ra nó sẽ được framework gọi ở bên trong trước khi gen html từ template
