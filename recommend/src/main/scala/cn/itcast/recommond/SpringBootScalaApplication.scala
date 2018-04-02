package cn.itcast.recommond

import org.apache.spark.{SparkConf, SparkContext}
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.{EnableAutoConfiguration, SpringBootApplication}
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.scheduling.annotation.EnableScheduling

/**
  * Created by lgh on 2018/3/22.
  */

@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
@EnableScheduling
class Config{
  @Bean
  def sparkContext: SparkContext = {
    val sparkConf: SparkConf = new SparkConf().setAppName("aa").setMaster("local[2]")
    new SparkContext(sparkConf);
  }
}
object SpringBootScalaApplication extends App {
  SpringApplication.run(classOf[Config])
}
