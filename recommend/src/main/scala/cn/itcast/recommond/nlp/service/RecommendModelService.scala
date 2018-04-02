package cn.itcast.recommond.nlp.service

import java.util
import java.util.List
import cn.itcast.recommond.constant.ModelConstantPath
import cn.itcast.recommond.nlp.vo.RecommendVo
import org.apache.spark.SparkContext
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
  * Created by lgh on 2018/3/26.
  */
@Service
class RecommendModelService(@Autowired() private val sparkContext: SparkContext) {
  def findByUserIdAndProductNum(uid: Int, num: Int): List[RecommendVo] = {
    val sc: SparkContext = sparkContext
    sc.setLogLevel("WARN")
    //加载模型文件
    val sameModel = MatrixFactorizationModel.load(sc, ModelConstantPath.RECOMMENDMODELPATH)
    /**
      * 此处应该时候用redis 进行数据校验，若返回null ，说明用户第一次使用,需要冷启动推送数据
      * 若模型返回异常，说明用户新注册
      */
    try {
      val resultList: List[RecommendVo] = new util.ArrayList[RecommendVo]()
      val rs = sameModel.recommendProducts(uid, num)
      //模型中获取数据
      val it = rs.iterator
      while (it.hasNext) {
        var vo = new RecommendVo();
        vo.productId = it.next().product
        resultList.add(vo)
      }
      return resultList
    } catch {
      case es: Exception => {
        println("用户未注册，需要冷启动")
      }
        //模型抛出异常需要冷启动数据
        val resultList: List[RecommendVo] = new util.ArrayList[RecommendVo]()
        var vo = new RecommendVo();
        //从数据库按照某种业务规则进行查询返回数据
        resultList.add(vo)
        resultList
    }
  }
}
