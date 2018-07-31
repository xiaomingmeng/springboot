package com.souche.springboot.web;

import com.souche.springboot.dao.dao.StudentDao;
import com.souche.springboot.dao.repository.StudentRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;


@Api("测试")
@RestController
@RequestMapping("/test")
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StringRedisTemplate template;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    KafkaTemplate kafkaTemplate;

    @ApiOperation("测试kafka")
    @PostMapping("/Kafka")
    @ResponseBody
    public void testkafka() throws Exception {
        kafkaTemplate.send("boot", UUID.randomUUID().toString());
    }


    @ApiOperation("测试redis添加")
    @ResponseBody
    @GetMapping("/addRedis/{qqq}")
    public String addRedis( @PathVariable String qqq){
        logger.info("进入接口参数为："+ qqq);
        setKey("redistest",qqq);
        StudentDao bySql = studentRepository.getById(1);
        System.out.println(bySql);
        return "你好"+"ok";
    }

    @ApiOperation("测试redis获取")
    @ResponseBody
    @GetMapping("/getRedis")
    public String getRedis(){
        logger.info("进入接口参数为：");
        return getValue("redistest");
    }


    @ApiOperation("测试springDateJpa添加")
    @ResponseBody
    @GetMapping("/addJpa/{data}")
    public StudentDao addJpa(@PathVariable String data){
        logger.info("进入接口参数为："+data);
        StudentDao studentDao = new StudentDao();
        studentDao.setName(data);
        studentRepository.save(studentDao);
        return studentDao;
    }


    @ApiOperation("测试springDateJpa获取")
    @ResponseBody
    @GetMapping("/getJpa/{id}")
    public StudentDao getJpa(@PathVariable Integer id){
        logger.info("进入接口参数为："+id);
        StudentDao byId = studentRepository.getById(id);
        return byId;
    }








    public  void setKey(String key,String value){
        ValueOperations<String, String> ops = template.opsForValue();
        ops.set(key,value);
    }

    public String getValue(String key){
        ValueOperations<String, String> ops = this.template.opsForValue();
        return ops.get(key);
    }

}

