package cn.dmdream.search.service;

import cn.dmdream.entity.vo.GameVo;
import cn.dmdream.utils.PageModel;

import java.util.List;

public interface GameSearchService {

    PageModel<GameVo> findByKeywordByPage(String keyword, Integer page, Integer pageSize);

}
