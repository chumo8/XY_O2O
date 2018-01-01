package com.xyo2o.common.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * 序列化反序列化工具
 * @author YLJ
 *
 */
public class SerializeUtil {
	/**
	 * 序列化
	 * @param obj
	 * @return
	 */
	public static byte[] serialize(Object obj){
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			//序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			byte[] byteArray = baos.toByteArray();
			return byteArray;
		} catch (Exception e) {
			System.err.println("序列化后的字符串存入一个字符类型的键中错误");
		}
		return null;
	}
	public static Object unSerialize(byte[] bytes){
		ByteArrayInputStream bais = null;
		try {
			//反序列化为对象
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (Exception e) {
			System.err.println("redis中没有该数据>>>>>>>>>>>>>>反序列化从redis中没有到数据");
		}
		return null;
	}
}
