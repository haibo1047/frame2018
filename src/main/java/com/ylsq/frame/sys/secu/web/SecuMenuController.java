package com.ylsq.frame.sys.secu.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ylsq.frame.common.base.BaseController;
import com.ylsq.frame.common.base.BaseModel;
import com.ylsq.frame.common.base.BaseService;
import com.ylsq.frame.common.base.SysParamEnum;
import com.ylsq.frame.common.base.ValidateResult;
import com.ylsq.frame.sys.secu.dao.model.SecuMenu;
import com.ylsq.frame.sys.secu.dao.model.SecuMenuExample;
import com.ylsq.frame.sys.secu.service.SecuMenuService;

@Controller
@RequestMapping("/sys/menu/")
public class SecuMenuController extends BaseController {
	private static Logger log = LoggerFactory.getLogger(SecuMenuController.class);
	
	@Autowired
	private SecuMenuService secuMenuService;
	
	
	
	@Override
	public String list(ModelMap modelMap) {
		// TODO Auto-generated method stub
		modelMap.put("moduleList", getParams(SysParamEnum.Menu_Module.getConstant()));
		return super.list(modelMap);
	}


	@Override
	protected void beforeEdit(ModelMap modelMap) {
		// TODO Auto-generated method stub
		modelMap.put("moduleList", getParams(SysParamEnum.Menu_Module.getConstant()));
		super.beforeEdit(modelMap);
	}

	protected ValidateResult validate(SecuMenu model) {
		SecuMenu existing = secuMenuService.selectByMenuName(model.getMenuName());
		if(existing != null && !(existing.getId().equals(model.getId()))) {
			log.warn("菜单已经存在："+ model.getMenuName());
			return new ValidateResult("菜单名称已经存在");
		}
		return ValidateResult.Passed;
	}
	@RequestMapping(value= "/save", method = RequestMethod.POST)
	public String save(SecuMenu menu,ModelMap modelMap) {
		log.debug(menu.toString());
		initModel(menu);
		
		ValidateResult vr = validate(menu);
		if(!vr.isPassed()) {
			modelMap.put("model", menu);
			modelMap.put("errorMsg", vr.getMsg());
			return webPrefix()+"edit";
		}
		if(menu.getId() == null) {
			secuMenuService.insert(menu);
		}
		else {
			secuMenuService.updateByPrimaryKey(menu);
		}
		return list(modelMap);
	}


	@Override
	protected List<? extends BaseModel> getModelList() {
		// TODO Auto-generated method stub
		List<SecuMenu> menuList = secuMenuService.selectByExample(new SecuMenuExample());
		return menuList;
	}

	@Override
	protected BaseService<SecuMenu,SecuMenuExample> getService() {
		// TODO Auto-generated method stub
		return secuMenuService;
	}

	@Override
	protected String webPrefix() {
		// TODO Auto-generated method stub
		return "/sys/secuMenu/";
	}

}