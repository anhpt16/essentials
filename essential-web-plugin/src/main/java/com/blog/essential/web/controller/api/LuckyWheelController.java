package com.blog.essential.web.controller.api;

import com.tvd12.ezyfox.util.EzyMapBuilder;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;

@Controller("/api/v1")
public class LuckyWheelController {

    @DoGet("/essential")
    public Object info() {
        return EzyMapBuilder.mapBuilder()
            .put("name", "essential")
            .build();
    }
}
