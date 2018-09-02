package com.ylsq.frame.sys.base.web;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ylsq.frame.common.base.BaseModelController;
import com.ylsq.frame.common.base.SysParamEnum;
import com.ylsq.frame.sys.base.dao.model.SysLog;
import com.ylsq.frame.sys.base.dao.model.SysLogExample;
import com.ylsq.frame.sys.base.service.SysLogService;



/**
 * Created by Harper.
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController extends BaseModelController {
    private static final Logger log = LoggerFactory.getLogger(SysLogController.class);
	
	@Autowired
	private SysLogService sysLogService;

	
	@Override
	public String list(@RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum, ModelMap modelMap) {
		// TODO Auto-generated method stub
		int pageSize = (int)SecurityUtils.getSubject().getSession().getAttribute("pageSize");
		List<SysLog> list = sysLogService.selectByExampleForStartPage(new SysLogExample(), pageNum, pageSize);
		modelMap.put("total",sysLogService.countByExample(new SysLogExample()));
		modelMap.put("logTypeList", getParams(SysParamEnum.Sys_Log_Type.getConstant()));
		modelMap.put("modelList", list);
		return webPrefix() + "list";
	}


	@Override
	protected String webPrefix() {
		// TODO Auto-generated method stub
		return "/sys/log/";
	}
}