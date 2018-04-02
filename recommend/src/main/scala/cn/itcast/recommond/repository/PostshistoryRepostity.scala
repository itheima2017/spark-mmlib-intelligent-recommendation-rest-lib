package cn.itcast.recommond.repository

import cn.itcast.recommond.entity.PostshistoryEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
/**
  * Created by lgh on 2018/3/22.
  */
@Repository
trait PostshistoryRepostity extends MongoRepository[PostshistoryEntity,String]{
}
