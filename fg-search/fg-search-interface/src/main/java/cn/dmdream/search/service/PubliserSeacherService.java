package cn.dmdream.search.service;

import cn.dmdream.entity.Publisher;
import cn.dmdream.utils.PageModel;

public interface PubliserSeacherService {
    PageModel<Publisher> findByKeywordByPage(String keyword, Integer page, Integer pageSize);
}
