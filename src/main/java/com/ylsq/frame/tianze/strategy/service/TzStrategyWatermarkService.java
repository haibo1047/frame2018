package com.ylsq.frame.tianze.strategy.service;

import com.ylsq.frame.common.base.BaseService;
import com.ylsq.frame.tianze.strategy.dao.model.TzStrategyWatermark;
import com.ylsq.frame.tianze.strategy.dao.model.TzStrategyWatermarkExample;

/**
* TzStrategyWatermarkService接口
* Created by harper
*/
public interface TzStrategyWatermarkService extends BaseService<TzStrategyWatermark, TzStrategyWatermarkExample> {
	
	/**
	 * 根据策略id查询
	 * @param strategyId
	 * @return
	 */
	public TzStrategyWatermark selectByStrategyId(Long strategyId);
	
}