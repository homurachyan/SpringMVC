package com.springmvc.dao;

import java.util.List;

import com.springmvc.entity.GlobalConfig;

public interface GlobalConfigDao {
	/**
	 * 查询出所有全局设置
	 * @param 
     * @return List<GlobalConfig>
	 * */
	public List<GlobalConfig> selectAllConfig();
	/**
	 * 查询出所有全局设置
	 * @param 
     * @return List<GlobalConfig>
	 * */	
	public int insertGlobalConfig(GlobalConfig globalconfig);
	/**
	 * 查询出所有全局设置
	 * @param 
     * @return List<GlobalConfig>
	 * */	
	public int updateGlobalConfig();
	/**
	 * 禁用一个全局设置
	 * @param 
     * @return List<GlobalConfig>
	 * */	
	public int disableGlobalConfig(String gid);
	
}
