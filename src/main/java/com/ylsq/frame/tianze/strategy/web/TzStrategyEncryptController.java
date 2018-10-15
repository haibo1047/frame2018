package com.ylsq.frame.tianze.strategy.web;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ylsq.frame.common.base.BaseExample;
import com.ylsq.frame.common.base.BaseModel;
import com.ylsq.frame.common.base.BaseModelController;
import com.ylsq.frame.common.base.BaseService;
import com.ylsq.frame.common.base.ValidateResult;
import com.ylsq.frame.tianze.strategy.dao.model.TzStrategyEncrypt;
import com.ylsq.frame.tianze.strategy.dao.model.TzStrategyEncryptExample;
import com.ylsq.frame.tianze.strategy.service.TzStrategyEncryptService;



/**
 * Created by Harper.
 */
@Controller
@RequestMapping("/tz/strategy/encrypt")
public class TzStrategyEncryptController extends BaseModelController {
    private static final Logger log = LoggerFactory.getLogger(TzStrategyEncryptController.class);
	
	@Autowired
	private TzStrategyEncryptService tzStrategyEncryptService;
	
	
	@Override
	public String list(@RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum,ModelMap modelMap) {
		// TODO Auto-generated method stub
		int pageSize = (int)SecurityUtils.getSubject().getSession().getAttribute("pageSize");
		List<TzStrategyEncrypt> list = tzStrategyEncryptService.selectByExampleForStartPage(new TzStrategyEncryptExample(),pageNum,pageSize);
		
		modelMap.put("modelList", list);
		modelMap.put("total", tzStrategyEncryptService.countByExample(new TzStrategyEncryptExample()));
		return webPrefix() + "list";
	}
	
	protected ValidateResult validate(TzStrategyEncrypt model) {
		return ValidateResult.Passed;
	}
	
	@RequestMapping(value= "/save", method = RequestMethod.POST)
	public String save(TzStrategyEncrypt model,ModelMap modelMap) {
		log.debug(model.toString());
		initModel(model);
		
		ValidateResult vr = validate(model);
		if(!vr.isPassed()) {
			modelMap.put("model", model);
			modelMap.put("errorMsg", vr.getMsg());
			return webPrefix()+"edit";
		}
		if(model.getId() == null) {
			tzStrategyEncryptService.insert(model);
		}
		else {
			tzStrategyEncryptService.updateByPrimaryKey(model);
		}
		return list(modelMap);
	}
	
	@Override
	protected BaseService<? extends BaseModel, ? extends BaseExample> getService() {
		// TODO Auto-generated method stub
		return tzStrategyEncryptService;
	}

	@Override
	protected List<? extends BaseModel> getModelList() {
		// TODO Auto-generated method stub
		List<TzStrategyEncrypt> list = tzStrategyEncryptService.selectByExample(new TzStrategyEncryptExample());
		return list;
	}

	@Override
	protected String webPrefix() {
		// TODO Auto-generated method stub
		return "/tz/strategy/encrypt/";
	}
}