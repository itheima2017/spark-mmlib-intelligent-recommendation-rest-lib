package cn.itcast.recommond.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import scala.beans.BeanProperty

/**
  * Created by lgh on 2018/3/22.
  */
@Document(collection = "postshistoryEntity")
class PostshistoryEntity extends Serializable{
 private val serialVersionUID = 6495975734995362273L
 @Id
 @BeanProperty
  var tid :String =_
 @BeanProperty
  var uid: String =_
 @BeanProperty
  var score :String =_

 override def toString = s"PostshistoryEntity($tid, $uid, $score)"
}
