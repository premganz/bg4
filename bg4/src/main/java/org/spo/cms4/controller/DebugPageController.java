package org.spo.cms4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class DebugPageController {
	
	@RequestMapping(value="/admin/debug", method=RequestMethod.GET)
	public String handlePageRequest_String( ) {
			return "debug";
		
	}
	
	
}
