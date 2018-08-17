package com.ylsq.frame.sys.base.sorter;

import java.util.Comparator;

import com.ylsq.frame.sys.base.dao.model.SysParamConfig;

public class ParamConfigSorter implements Comparator<SysParamConfig> {

	@Override
	public int compare(SysParamConfig o1, SysParamConfig o2) {
		// TODO Auto-generated method stub
		if(o1 == null && o2 == null)
			return 0;
		if(o1 == null)
			return -1;
		if(o2 == null)
			return 1;
		return o1.getConfigOrder() - o2.getConfigOrder();
	}

}
