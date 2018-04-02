package cn.itcast.recommond.controller

import cn.itcast.recommond.entity.PostshistoryEntity
import cn.itcast.recommond.service.PostshistoryService
import org.springframework.web.bind.annotation._
import org.springframework.beans.factory.annotation.Autowired
import java.util.List
import cn.itcast.recommond.nlp.service.RecommendModelService
import cn.itcast.recommond.nlp.vo.RecommendVo
/**
  * Created by lgh on 2018/3/22.
  */
@RestController
class PostshistoryController  @Autowired()(private val postshistoryService: PostshistoryService,
                                           private val recommendModelService:RecommendModelService)  {
  @RequestMapping(value = Array("/save"), method = Array(RequestMethod.POST))
  def save(@RequestBody postshistoryEntity : PostshistoryEntity) : Unit = {
    postshistoryService.save(postshistoryEntity)
  }

  @RequestMapping(value = Array("/list"), method = Array(RequestMethod.GET))
  def list() : List[PostshistoryEntity] = {
    postshistoryService.findAll()
  }

  @RequestMapping(value = Array("/findByUserIdAndProductNum"), method = Array(RequestMethod.GET))
  def findByUserIdAndProductNum(uid:Int,num:Int):List[RecommendVo]  ={
    recommendModelService.findByUserIdAndProductNum(uid,num)
  }
}
