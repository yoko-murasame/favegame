package cn.dmdream.search.service.impl;

import cn.dmdream.entity.Game;
import cn.dmdream.entity.vo.GameVo;
import cn.dmdream.game.service.GameService;
import cn.dmdream.search.service.GameSearchService;
import cn.dmdream.utils.JsonMsg;
import cn.dmdream.utils.PageModel;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service(interfaceClass = GameSearchService.class)
public class GameSearchServiceImpl implements GameSearchService {

    @Autowired
    private SolrTemplate solrTemplate;
    @Reference
    private GameService gameService;

    private JsonMsg jsonMsg;

    // 更新全部
    @JmsListener(destination = "updateAllToSolr")
    public void listenUpdateOrDeleteAll(String message){
        try {
            if (message.equalsIgnoreCase("update")) {
                Game game = new Game();
                game.setIsValid(1);
                JsonMsg list = gameService.findAllGameVoByPage(1, 1000000, game, null);
                SimpleQuery query = new SimpleQuery("*:*");
                solrTemplate.delete("game", query);
                PageModel<GameVo> voPageModel = (PageModel<GameVo>) list.getData();
                List<GameVo> voList = voPageModel.getList();
                solrTemplate.saveBeans("game", voList);
                System.out.println("正在更新索引库");
            } else {
                SimpleQuery query = new SimpleQuery("*:*");
                solrTemplate.delete("game", query);
                System.out.println("正在清空索引库");
            }
            solrTemplate.commit("game");
            System.out.println("操作成功完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 监听新增/修改/删除
    @JmsListener(destination = "fg-game-search-update")
    public void listenSaveOrUpdate(String message){
        try {
            JsonMsg jm = gameService.findGameVoById(Integer.parseInt(message));
            GameVo gameVo = (GameVo) jm.getData();
            if (gameVo == null) {
                //找不到,删除
                solrTemplate.deleteByIds("game", message);
                System.out.println("索引库成功删除一条记录");
            } else {
                //新增/保存
                solrTemplate.deleteByIds("game", message);
                solrTemplate.saveBean("game", gameVo);
                System.out.println("索引库成功更新一条记录");
            }
            solrTemplate.commit("game");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * solr根据五个字段查询
     * @param keyword
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public JsonMsg findByKeywordByPage(String keyword, Integer page, Integer pageSize) {

        try {

            if(keyword == null || keyword.equals("")) return null;
            // 创建solrQuery对象,封装条件
            SimpleHighlightQuery query = new SimpleHighlightQuery();
            //条件查询
            Criteria criteria = new Criteria("gmKeyword").is(keyword);
            //将条件添加到查询对象
            query.addCriteria(criteria);

            //创建高亮对象,添加高亮操作
            HighlightOptions highlightOptions = new HighlightOptions();
            highlightOptions.addField("gmName");
            highlightOptions.addField("gmPublisherName");
            highlightOptions.addField("gmTypeName");
            highlightOptions.addField("gmOperatorName");
            highlightOptions.addField("gmTag");
            highlightOptions.setSimplePrefix("<font color='red'>");
            highlightOptions.setSimplePostfix("</font>");
            //启用多字段高亮
            highlightOptions.addHighlightParameter("hl.preserveMulti", "true");

            //设置高亮查询
            query.setHighlightOptions(highlightOptions);

            // 设置分页条件
            // 设置分页查询起始位置
            query.setOffset((long) ((page-1) * pageSize));
            // 设置分页查询每页显示条数
            query.setRows(pageSize);

            // 执行查询
            HighlightPage<GameVo> highlightPage = solrTemplate.queryForHighlightPage("game", query, GameVo.class);

            // 获取高亮分页记录
            List<GameVo> list = highlightPage.getContent();

            // 获取总记录数
            long totalElements = highlightPage.getTotalElements();

            //循环搜索小说集合,获取高亮
            for (GameVo gameVo : list) {
                //获取高亮
                List<HighlightEntry.Highlight> highlights = highlightPage.getHighlights(gameVo);

                //判断高亮是否存在
                if(highlights != null  && highlights.size() > 0){
                    //多个域字段,开始遍历
                    for (HighlightEntry.Highlight highlight : highlights) {
                        //获取高亮的域字段名字
                        String fieldName = highlight.getField().getName();
                        //获取高亮值[{}]
                        List<String> snipplets = highlight.getSnipplets();
                        //设置高亮字段
                        if(fieldName.equals("gmName")){
                            gameVo.setGmName(snipplets.get(0));
                        }else if(fieldName.equals("gmPublisherName")){
                            gameVo.setGmPublisherName(snipplets.get(0));
                        }else if(fieldName.equals("gmTypeName")){
                            gameVo.setGmTypeName(snipplets.get(0));
                        }else if(fieldName.equals("gmOperatorName")){
                            gameVo.setGmOperatorName(snipplets.get(0));
                        }else if(fieldName.equals("gmTag")){
                            gameVo.setGmTag(snipplets.get(0));
                        }
                    }
                }
            }

            PageModel<GameVo> pageModel = new PageModel<GameVo>();
            PageModel.wrapPageModel(page, pageSize, (int) totalElements, highlightPage.getContent(), pageModel);
            jsonMsg = JsonMsg.makeSuccess("查询成功", pageModel);
        } catch (Exception e) {
            e.printStackTrace();
            jsonMsg = JsonMsg.makeFail("查询失败:" + e.getMessage(), null);
        }
        return jsonMsg;
    }

}
