package com.blog.essential.admin.test;

import com.tvd12.ezyhttp.server.boot.EzyHttpApplicationBootstrap;
import com.tvd12.ezyhttp.server.core.annotation.ComponentsScan;
import com.tvd12.ezyhttp.server.core.annotation.PropertiesSources;

@PropertiesSources({
    "config.properties",
    "setup.properties"
})
@ComponentsScan({
    "org.youngmonkeys.ezyplatform",
    "com.blog.essential",
    "org.youngmonkeys.ezyarticle"
})
public class EssentialAdminPluginStartupTest {

    public static void main(String[] args) throws Exception {
        EzyHttpApplicationBootstrap.start(EssentialAdminPluginStartupTest.class);
    }
}
