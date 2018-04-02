package cn.itcast.recommond.task;

import cn.itcast.recommond.constant.ModelConstantPath;
import cn.itcast.recommond.entity.PostshistoryEntity;
import cn.itcast.recommond.nlp.create.dataset.CreateRecommendDataSet;
import cn.itcast.recommond.nlp.model.TranRecommendModel;
import cn.itcast.recommond.service.PostshistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Created by lgh on 2018/3/26.
 */
@Component
public class RecommendScheduledTask {
    @Autowired
    private PostshistoryService postshistoryService;
    @Autowired
    private TranRecommendModel tranRecommendModel;
    //每小时的55分钟 28秒程序触发执行一次
    @Scheduled(cron = "28 55 * * * ?")
    public void recommendScheduledTaskTimer() {
        try {
            //加载数据集生成数据文件
         List<PostshistoryEntity> postshistoryEntityList = postshistoryService.findAll();
         CreateRecommendDataSet.writerTranRecommendModelFile(postshistoryEntityList, ModelConstantPath.RECOMMENDMODELFILEPATH);
            //训练模型
            tranRecommendModel.writerTranRecommondModelFile();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }
}
