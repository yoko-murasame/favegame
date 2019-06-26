package cn.dmdream.search.service;

import cn.dmdream.utils.JsonMsg;

public interface GameSearchService {

    JsonMsg findByKeywordByPage(String keyword, Integer page, Integer pageSize);

}
