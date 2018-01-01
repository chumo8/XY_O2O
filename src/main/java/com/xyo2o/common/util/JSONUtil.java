package com.xyo2o.common.util;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 * JSON工具类
 * @author YLJ
 *
 */
public enum JSONUtil {
	INSTANCE;
	/**
	 * 返回字符串
	 * @param response
	 * @param result
	 * @param msg
	 */
	public void putJSON(HttpServletResponse response,boolean result,String msg){
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", result);
			map.put("msg", msg);
			String json = JSON.toJSONString(map);
			System.err.println("json"+json);
			out.write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 返回string集合
	 * @param response
	 * @param result
	 * @param list
	 */
	public void putJSONStringList(HttpServletResponse response,boolean result,List<String> list){
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", result);
			map.put("list", list);
			String json = JSON.toJSONString(map);
			System.err.println("jsonList--"+json);
			out.write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 返回list集合
	 * @param response
	 * @param result
	 * @param list
	 */
	public <T> void putJSONList(HttpServletResponse response,boolean result,List<T> list){
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", result);
			map.put("list", list);
			String json = JSON.toJSONString(map);
			System.err.println(json);
			out.write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 返回object
	 * @param response
	 * @param result
	 * @param obj
	 */
	public <T> void putJSONObj(HttpServletResponse response,boolean result,Object obj){
		response.setContentType("text/html;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("result", result);
			map.put("Object", obj);
			String json = JSON.toJSONString(map);
			out.write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}