package top.autuan.profile.modular.base.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * 前端展示的项目实体对象
 */
public class Project {
    /**
     * id
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 简介
     */
    private String summary;
    /**
     * 跳转路径
     */
    private String url;
    /**
     * 图片
     */
    private String image;
    /**
     * 排序值
     */
    private Integer sequence;
}
