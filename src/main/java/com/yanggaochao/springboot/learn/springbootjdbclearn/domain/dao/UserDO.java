package com.yanggaochao.springboot.learn.springbootjdbclearn.domain.dao;

/**
 * 用户实体对象
 *
 * @author 杨高超
 * @since 2018-03-09
 */
public class UserDO {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
