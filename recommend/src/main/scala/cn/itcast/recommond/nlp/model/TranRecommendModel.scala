package cn.itcast.recommond.nlp.model


import java.io.File

import cn.itcast.recommond.constant.ModelConstantPath
import cn.itcast.recommond.utils.FileUtils
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.recommendation.{ALS, Rating}
import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.stereotype.Service

/**
  * Created by lgh on 2018/3/23.
  */
@Service
class TranRecommendModel (@Autowired()private val sparkContext:SparkContext) {
  def writerTranRecommondModelFile: Unit = {
    println("模型训练开始")
    val sc: SparkContext = sparkContext
    sc.setLogLevel("WARN")
      //读取本地模型文件方法  读取文件可以是hadoop hdfs 文件或者本地文件
      val data = sc.textFile(ModelConstantPath.RECOMMENDMODELFILEPATH)
      val ratings = data.map(_.split(',') match { case Array(user, item, rate) =>
        Rating(user.toInt, item.toInt, rate.toDouble)
      })
      //构建ALS算法
      //   rating：由用户-物品矩阵构成的训练集
      //   rank：隐藏因子的个数
      //   numIterations: 迭代次数，训练次数越多越精准,次数多容易内存溢出
      val rank = 10
      val numIterations = 10
      val model = ALS.train(ratings, rank, numIterations, 0.01)

      //从ratings中获取值包含用户商品的数据集  ->     =>
      val usersProducts = ratings.map { case Rating(user, product, rate) =>
        (user, product)
      }
      //使用推荐模型对用户进行预测评分，返回预测评分数据集
      val predictions =
        model.predict(usersProducts).map { case Rating(user, product, rate) =>
          ((user, product), rate)
        }
      //将真实评分数据集与预测评分数据集进行合并
      val ratesAndPreds = ratings.map { case Rating(user, product, rate) =>
        ((user, product), rate)
      }.join(predictions)

      //计算均方差值  均方误差（mean-square error, MSE）是反映估计量与被估计量之间差异程度的一种度量
      //MSE可以评价数据的变化程度，MSE的值越小，说明预测模型描述实验数据具有更好的精确度。
      val MSE = ratesAndPreds.map { case ((user, product), (r1, r2)) =>
        val err = (r1 - r2)
        err * err
      }.mean()
    //生成模型文件之前，先删除原有模型文件，要不保存模型跑出异常
    val file = new File(ModelConstantPath.RECOMMENDMODELPATH)
    FileUtils.deleteDir(file)
    FileUtils.mkdirFile(file)
    //保存模型
    model.save(sc,ModelConstantPath.RECOMMENDMODELPATH)
    println("模型训练结束")
    }
}
