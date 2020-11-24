package top.autuan.profile.modular.base.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import top.autuan.profile.modular.base.entity.PageInfo;
import top.autuan.profile.modular.base.entity.Project;
import top.autuan.profile.modular.base.service.BaseService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BaseServiceImpl implements BaseService {
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public PageInfo pageInfos() {
        log.info("BaseServiceImpl -> pageInfo -> start");
        redisTemplate.opsForValue().get("info");
        return null;
    }

    @Override
    public List<Project> listProjects() {
        Map<Object, Object> pages = redisTemplate.opsForHash().entries("pages");
        return null;
    }
}
