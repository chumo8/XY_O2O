package com.xyo2o.web.controller.store;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.xyo2o.domain.store.Store;
import com.xyo2o.service.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/store")
public class StoreController {
	@Autowired
	private StoreService storeService;
	
	@RequestMapping("/list")
	public String findAll(HttpServletRequest request){
		System.out.println("list");
		List<Store> list = storeService.findAll();
		request.setAttribute("list", list);
		return "store/store";
	}
	
	
}
