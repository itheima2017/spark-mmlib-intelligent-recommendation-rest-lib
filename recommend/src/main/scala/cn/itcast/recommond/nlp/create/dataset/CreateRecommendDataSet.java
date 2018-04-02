package cn.itcast.recommond.nlp.create.dataset;

import cn.itcast.recommond.entity.PostshistoryEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lgh on 2018/3/23.
 */
@Service
public class CreateRecommendDataSet {
    public static void writerTranRecommendModelFile(List<PostshistoryEntity> postshistoryEntityList,String path) throws Exception {
        FileOutputStream fos = null;
        try {
            File file = new File(path);
            if(file.exists()){
                file.delete();
            }
            fos = new FileOutputStream(file);
            for (PostshistoryEntity postshistoryEntity : postshistoryEntityList) {
                String str = postshistoryEntity.getUid() + "," + postshistoryEntity.getTid() + "," + postshistoryEntity.getScore();
                fos.write(str.getBytes());
                fos.write("\r\n".getBytes());
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }
}
