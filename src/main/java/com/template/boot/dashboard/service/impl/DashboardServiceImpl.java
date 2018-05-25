package com.template.boot.dashboard.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.template.boot.dashboard.mapper.DashboardMapper;
import com.template.boot.dashboard.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private DashboardMapper dashboardMapper;
	
	public int selectOne() {
		return dashboardMapper.selectOne();
	}
}
