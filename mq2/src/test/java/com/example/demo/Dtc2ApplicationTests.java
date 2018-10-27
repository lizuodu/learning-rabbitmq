package com.example.demo;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.example.demo.mapper.StoreMapper;
import com.example.demo.model.Store;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Dtc2ApplicationTests {
	
	@Resource
	private StoreMapper storeMapper;

	@Test
	public void contextLoads() {
		
		Store store = this.storeMapper.findByProductCode("cookie");
		System.out.println(JSON.toJSONString(store));
		
	}

}
