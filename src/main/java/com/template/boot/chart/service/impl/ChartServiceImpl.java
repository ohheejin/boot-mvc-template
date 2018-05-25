package com.template.boot.chart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.template.boot.chart.mapper.ChartMapper;
import com.template.boot.chart.service.ChartService;

@Service
public class ChartServiceImpl implements ChartService {

	@Autowired
	private ChartMapper chartMapper;
	
	@Override
	public int selectOne() {
		return chartMapper.selectOne();
	}

	
}
