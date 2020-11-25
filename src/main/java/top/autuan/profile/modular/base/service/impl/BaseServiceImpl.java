package top.autuan.profile.modular.base.service.impl;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import top.autuan.profile.modular.base.entity.PageInfo;
import top.autuan.profile.modular.base.entity.Project;
import top.autuan.profile.modular.base.service.BaseService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BaseServiceImpl implements BaseService {
    private RedisTemplate<String,String> redisTemplate;

    @Autowired(required = false)
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        this.redisTemplate = redisTemplate;
    }

    /**
     * redis key
     */
    private final String KEY_INFO = "info";
    private final String KEY_LIST = "list";
    @Override
    public PageInfo pageInfos() {
        log.info("BaseServiceImpl -> pageInfo -> start");
        String infoStr = redisTemplate.opsForValue().get(KEY_INFO);
        PageInfo pageInfo = JSONUtil.toBean(infoStr, PageInfo.class);
        return pageInfo;
    }

    @Override
    public List<Project> listProjects() {
        Map<Object, Object> map = redisTemplate.opsForHash().entries(KEY_LIST);
        List<Project> collect = map.values().stream()
                .map(item ->JSONUtil.toBean(String.valueOf(item),Project.class))
                .sorted(Comparator.comparing(Project::getSequence).reversed())
                .collect(Collectors.toList());
        return collect;
    }

    @Override
    public void addPageInfo(PageInfo pageInfo) {
        String str = JSONUtil.toJsonStr(pageInfo);
        redisTemplate.opsForValue().set(KEY_INFO,str);
    }

    @Override
    public void addProjects(List<Project> projects) {
        Map<String, Project> map = projects.stream()
                .collect(Collectors.toMap(Project::getId, Function.identity(), (o, n) -> n));
        redisTemplate.opsForHash().putAll(KEY_LIST,map);
    }

    @Override
    public void deleteProject(String id) {
        redisTemplate.opsForHash().delete(KEY_LIST,id);
    }
}
