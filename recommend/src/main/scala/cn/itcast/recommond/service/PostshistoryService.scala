package cn.itcast.recommond.service

import java.util.List

import cn.itcast.recommond.entity.PostshistoryEntity
import cn.itcast.recommond.repository.PostshistoryRepostity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * Created by lgh on 2018/3/22.
  */
@Service
class PostshistoryService {
 @Autowired var postshistoryRepostity: PostshistoryRepostity = _

 def findAll(): List[PostshistoryEntity] ={
  postshistoryRepostity.findAll()
 }

 def save(postshistoryEntity:PostshistoryEntity): Unit={
    postshistoryRepostity.save(postshistoryEntity)
 }
 def findByUserIdAndProductNum(id:String): PostshistoryEntity={
  postshistoryRepostity.findOne(id)
 }


}
