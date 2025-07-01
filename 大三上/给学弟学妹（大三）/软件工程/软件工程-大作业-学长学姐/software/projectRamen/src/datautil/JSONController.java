package datautil;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.parser.Feature;

/**
*  The JSONController class provides some simple methods for JSON input or output.
*  Title      : JSONController.java
*  <p>
*  Description:
*  <p>
*  The JSONController class provides some simple methods for JSON input or output.
*  <p>
*  The most important feature of this class is that it can allow any class to be
*  saved in the JSON file
*  @author  Tianyi Liu
*  @version 1.1
*/
public class JSONController {
	IOController io;
	
	/** Constructor of CSVController
	* @param filename the name of the CSV file which needs to be I/O.
	*/
	public JSONController(String filename) {
		io=new IOController(filename);
        int features = 0;
        features |= Feature.AutoCloseSource.getMask();
        features |= Feature.InternFieldNames.getMask();
        features |= Feature.AllowUnQuotedFieldNames.getMask();
        features |= Feature.AllowSingleQuotes.getMask();
        features |= Feature.AllowArbitraryCommas.getMask();
        features |= Feature.SortFeidFastMatch.getMask();
        features |= Feature.IgnoreNotMatch.getMask();
        JSON.DEFAULT_PARSER_FEATURE = features;
	}
	
	/** 
	* read an array of objects from the JSON file
	* @param tClass the class information of the objects in the file
	* @param <T> Any javabean object
	* @return the array contains the objects which are read from the file
	*/
	public <T> List<T> readArray(Class<T> tClass) {
		String json=io.directRead();
		List<T> ret = JSONArray.parseArray(json,tClass); 
		return ret;
	}
	
	/** 
	* write an array of objects to the JSON file
	* @param objectList the List contains the objects which will be written to the file
	* @return Whether the write operation was successful
	*/
	public boolean writeArray(List<?> objectList) {
		String temp= (String) JSON.toJSONString(objectList);
		return io.directWrite(temp);
	}
	
	/** 
	* read a single object from the JSON file
	* @param tClass the class information of the object
	* @return the object which is read from the file
	*/
	public Object read(Class<?> tClass) {
		String json=io.directRead();
		Object ret = JSON.parseObject(json,tClass); 
		return ret;
	}
	
	/** 
	* write a single object to the JSON file
	* @param x the object which is need to be written to the file
	* @return Whether the write operation was successful
	*/
	public boolean write(Object x) {
		String temp= (String) JSON.toJSONString(x);
		return io.directWrite(temp);
	}
}
