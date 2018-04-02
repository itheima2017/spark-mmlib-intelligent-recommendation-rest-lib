# spark-mmlib-intelligent-recommendation-rest-lib
智能推荐rest库

介绍
推荐算法根据用户的历史行为，挖掘出用户的喜好，并为用户推荐与其喜好相符的商品或者信息。
此案例是一个代码示例,包括机器学习模型设计、模型训练、生成模型、简易调度。把个性化推荐发布称rest服务，供外系统调用。

开发语言:java scala

技术点：
算法库：spark mllib als
后台技术:springboot 、springcloud、 mongodb 、spark scala等

运行环境：jdk1.8 scala2.11.7
 
 
web访问地址  
uid:代表用户编号
num:需要推荐产品个数
/findByUserIdAndProductNum?uid=1&num=10
@RequestMapping(value = Array("/findByUserIdAndProductNum "), method = Array())  uid:Int,num:Int
  def findByUserIdAndProductNum():List[RecommendVo]  ={
    recommendModelService.findByUserIdAndProductNum(uid,num)
  }


程序运行入口
SpringBootScalaApplication

