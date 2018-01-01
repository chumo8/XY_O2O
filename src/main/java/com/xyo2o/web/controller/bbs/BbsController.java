package com.xyo2o.web.controller.bbs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bbs")
public class BbsController {
	@RequestMapping("/bbs_index")
	public String bbsShow(){
		return "bbs/bbs_index";
		
	}
}
