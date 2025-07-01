package datautil;

import java.beans.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

/**
*  The CSVController class provides some simple methods for CSV input or output.
*  Title      : CSVController.java
*  <p>
*  Description:
*  <p>
*  The CSVController class provides some simple methods for CSV input or output.
*  <p>
*  The most important feature of this class is that it can allow any class
*  which has no sub object.
*  @author  Tianyi Liu
*  @version 1.3
*/
public class CSVController {	
	private String filename;
	private File file;
	
	/** Constructor of CSVController
	* @param filename the name of the CSV file which needs to be I/O.
	*/
	public CSVController(String filename) {
		this.filename=filename;
		file=new File(this.filename);
	}
	
	/**
	* Get the property descriptor of one class
	* @param tClass the class which is required to generate the map
	* @return Field map property descriptor
	*/
    private static Map<String, PropertyDescriptor> getCsvFieldMapPropertyDescriptor(Class<? extends Object> tClass) throws Exception {
        Map<String, PropertyDescriptor> descriptorMap = new HashMap<String, PropertyDescriptor>();
        BeanInfo beanInfo = Introspector.getBeanInfo(tClass);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if (propertyDescriptor.getWriteMethod() == null) {
                continue;
            }
            Field field = tClass.getDeclaredField(propertyDescriptor.getName());
            String fieldMetaName = field.getName();
            descriptorMap.put(fieldMetaName, propertyDescriptor);
        }
        return descriptorMap;
    }
    
	/**
	* Get the file header of one class
	* The file header is the first row of the csv
	* @param tClass the class which is required to generate the map
	* @return an array which contains the file header
	*/
    public static String[] getFileHeader(Class<? extends Object> tClass) {
    	BeanInfo beanInfo;
    	ArrayList<String>ret=new ArrayList<String>();
		try {
			beanInfo = Introspector.getBeanInfo(tClass);
	    	PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
	        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
	            if (propertyDescriptor.getWriteMethod() == null) {
	                continue;
	            }
	            Field field = tClass.getDeclaredField(propertyDescriptor.getName());
	            String fieldMetaName = field.getName();
	            ret.add(fieldMetaName);
	        }
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
		String res[] = new String[ret.size()];
		for(int i=0;i<ret.size();i++) {
			res[i]=ret.get(i);
		}
		return res;
    }
    
	private boolean write(List<Object> objectList, String[] fileHeader, boolean mod) {
		CSVFormat format=null;
		if(!file.exists()) {
			mod=false;
		}
		if(mod==false) {
			format = CSVFormat.DEFAULT.withHeader(fileHeader).withRecordSeparator("\n");
		} else {
			format= CSVFormat.DEFAULT.withRecordSeparator("\n").withFirstRecordAsHeader();
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < fileHeader.length; i++) {
            map.put(fileHeader[i], i);
        }
        try {
            Map<String, PropertyDescriptor> descriptorMap = null;
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter
            		(new FileOutputStream(this.file,mod), "UTF-8"));
            CSVPrinter printer = new CSVPrinter(bw, format);
            for (Object object : objectList) {
            	if(descriptorMap==null) {
            		descriptorMap = CSVController.getCsvFieldMapPropertyDescriptor(object.getClass());
            	}
                String[] records = new String[fileHeader.length];
                for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
                    if (descriptorMap.containsKey(stringIntegerEntry.getKey())) {
                    	Class<?> type=descriptorMap.get(stringIntegerEntry.getKey())
                		.getReadMethod().invoke(object).getClass();
                    	if(type==Date.class) {
                    		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S");
                    		String now=df.format(descriptorMap.get(stringIntegerEntry.getKey())
                            		.getReadMethod().invoke(object));
                    		records[map.get(stringIntegerEntry.getKey())] = now;
                    	} else {
                        records[map.get(stringIntegerEntry.getKey())] = 
                        		String.valueOf(descriptorMap.get(stringIntegerEntry.getKey())
                        		.getReadMethod().invoke(object));
                    	}
                    }
                }
                printer.printRecord(Arrays.asList(records));
            }
            bw.flush();
            bw.close();
            printer.close();
        } catch (Exception e) {
        	e.printStackTrace();
        	return false;
        }
        return true;
	}
	
	private <T> List<T> _read(Class<T> tClass) {
        CSVFormat format = CSVFormat.DEFAULT
        		.withFirstRecordAsHeader()
        		.withRecordSeparator("\n");
        List<T> tList = new ArrayList<>();
        try {
            Map<String, PropertyDescriptor> descriptorMap = getCsvFieldMapPropertyDescriptor(tClass);
            FileReader fileReader = new FileReader(file);
            CSVParser parser = new CSVParser(fileReader, format);
            Map<String, Integer> map = parser.getHeaderMap();
            for (CSVRecord record : parser) {
                T t = tClass.getDeclaredConstructor().newInstance();
                for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
                    if (descriptorMap.containsKey(stringIntegerEntry.getKey()) && 
                    		record.size() > stringIntegerEntry.getValue()) {
                    	Class<?> type=descriptorMap.get(stringIntegerEntry.getKey()).getPropertyType();
                    	if(type==int.class||type==Integer.class) {
                    		descriptorMap.get(stringIntegerEntry.getKey())
                            .getWriteMethod().invoke(t, Integer.parseInt(record.get(stringIntegerEntry.getValue())));
                    	} else if(type==double.class||type==Double.class) {
                    		descriptorMap.get(stringIntegerEntry.getKey())
                            .getWriteMethod().invoke(t, Double.parseDouble(record.get(stringIntegerEntry.getValue())));
                    	} else if(type==float.class||type==Float.class) {
                    		descriptorMap.get(stringIntegerEntry.getKey())
                            .getWriteMethod().invoke(t, Float.parseFloat(record.get(stringIntegerEntry.getValue())));
                    	} else if(type==Date.class) {
                    		DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S");
                    		descriptorMap.get(stringIntegerEntry.getKey())
                            .getWriteMethod().invoke(t,df.parse(record.get(stringIntegerEntry.getValue())));
                    	} else {
	                        descriptorMap.get(stringIntegerEntry.getKey())
	                        .getWriteMethod().invoke(t, record.get(stringIntegerEntry.getValue()));
                    	}
                    }
                }
                tList.add(t);
            }
            parser.close();
            fileReader.close();
        } catch (Exception e) {
			e.printStackTrace();
			return tList;
        }
        return tList;
    }
	
	/**
	* Overwrites a group of objects to a file
	* @param objectList the list of the objects
	* @param fileHeader the header of the csv file
	* @return Whether it was written successfully or not
	*/
	public boolean coverWrite(List objectList, String[] fileHeader) {
		return this.write(objectList, fileHeader, false);
	}
	
	/**
	* Appends a group of objects to a file
	* @param objectList the list of the objects
	* @param fileHeader the header of the csv file
	* @return Whether it was written successfully or not
	*/
	public boolean appendWrite(List objectList, String[] fileHeader) {
		return this.write(objectList, fileHeader, true);
	}
	
	/**
	* Overwrites an object to a file
	* @param obj the object
	* @param fileHeader the header of the csv file
	* @return Whether it was written successfully or not
	*/
	public boolean coverWriteOne(Object obj, String[] fileHeader) {
		List<Object> temp=new ArrayList<Object>();
		temp.add(obj);
		return this.write(temp, fileHeader, false);
	}
	
	/**
	* Appends an object to a file
	* @param obj the object
	* @param fileHeader the header of the csv file
	* @return Whether it was written successfully or not
	*/
	public boolean appendWriteOne(Object obj, String[] fileHeader) {
		List<Object> temp=new ArrayList<Object>();
		temp.add(obj);
		return this.write(temp, fileHeader, true);
	}
	
	/**
	* Read objects from a csv file
	* @param <T> Any javabean object that does not contain children objects
	* @param tClass the class which is required to read the csv
	* @return The list of objects that are read from csv file
	*/
	public <T> List<T> read(Class<T> tClass) {
		return this._read(tClass);
	}
	
}


