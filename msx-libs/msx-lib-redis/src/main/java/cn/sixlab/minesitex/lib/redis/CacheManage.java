/**
 * Copyright (c) 2017 Sixlab. All rights reserved.
 * <p>
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 * <p>
 * For more information, please see
 * https://sixlab.cn/
 *
 * @time: 2017/10/26 01:17
 * @author: Patrick <root@sixlab.cn>
 */
package cn.sixlab.minesitex.lib.redis;

import cn.sixlab.minesitex.lib.base.util.JsonUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CacheManage {
    
    @Autowired
    private StringRedisTemplate template;
    
    public String get(String key) {
        return template.opsForValue().get(key);
    }
    
    public <T> T get(String key, Class<T> clz) {
        String value = template.opsForValue().get(key);
        return JsonUtl.toBean(value, clz);
    }
    
    public void del(String key) {
        template.delete(key);
    }
    
    public void put(String key, Object val) {
        template.opsForValue().set(key, JsonUtl.toJson(val));
    }
    
    public void put(String key, Object val, long sec) {
        template.opsForValue().set(key, JsonUtl.toJson(val), sec, TimeUnit.SECONDS);
    }
}
