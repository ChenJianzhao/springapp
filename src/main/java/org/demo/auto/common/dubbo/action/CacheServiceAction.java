package org.demo.auto.common.dubbo.action;

import com.alibaba.dubbo.config.annotation.Reference;
import org.demo.auto.common.dubbo.service.ICacheService;
import org.springframework.stereotype.Component;

/**
 * Created by cjz on 2017/12/12.
 */
@Component
public class CacheServiceAction  implements ICacheService{

    ICacheService cacheService = null;

    public ICacheService getCacheService() {
        return cacheService;
    }

    @Reference(cache = "lru")
    public void setCacheService(ICacheService cacheService) {
        this.cacheService = cacheService;
    }

    public String cachedSayHello(String name) {
        return cacheService.cachedSayHello(name);
    }
}
