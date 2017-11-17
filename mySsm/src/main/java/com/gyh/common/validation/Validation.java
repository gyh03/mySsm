package com.gyh.common.validation;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gyh.common.exception.CustomException;
import com.gyh.common.exception.CustomException;
import com.gyh.utils.json.JacksonUtils;

@Component
public class Validation {
	
	private static final Logger log = LoggerFactory.getLogger(Validation.class);

	/**
	 * 通过JSON串获取对象并校验字段
	 * @param s json串
	 * @param classType 对象类型
	 * @param fieldNames 需要校验的字段
	 * @return 对象
	 */
	public <T> T getObject(T t, String[] fieldNames) {
		// get object from json
		/*T t = null;
		try {
			t = JacksonUtils.fromJson(s, classType);
		} catch (Exception e) {
			throw new CustomException("input data is an invalid json string", e);
		}*/

		if (fieldNames == null || fieldNames.length == 0) {
			return t;
		}
		
		// validate
		List<Field> fieldList = new LinkedList<Field>();
		for (String fieldName : fieldNames) {
			Field field;
			try {
				field = t.getClass().getDeclaredField(fieldName);
			} catch (Exception e) {
				log.warn("can't get field named:" + fieldName);
				continue;
			}
			fieldList.add(field);
		}

		for (Field field: fieldList) {
			Object fieldObject;
			String name = field.getName();
			
			try {
				field.setAccessible(true);
				fieldObject = field.get(t);
			} catch (Exception e) {
				throw new CustomException(e);
			}
			
			if (field.isAnnotationPresent(NotNull.class)) {
				checkNotNull(fieldObject, name);
			} else if (field.isAnnotationPresent(IsInt.class)) {
				long min = field.getAnnotation(IsInt.class).min();
				long max = field.getAnnotation(IsInt.class).max();
				checkIsInt(fieldObject, name, min, max);
			} else if (field.isAnnotationPresent(IsString.class)) {
				int min = field.getAnnotation(IsString.class).minLength();
				int max = field.getAnnotation(IsString.class).maxLength();
				checkIsString(fieldObject, name, min, max);
			} else if (field.isAnnotationPresent(InInt.class)) {
				long[] value = field.getAnnotation(InInt.class).value();
				checkInInt(fieldObject, name, value);
			}
		}
		return t;
	}

	private void checkNotNull(Object object, String fieldName) {
		if (object == null) {
			throw new CustomException("");
		}
	}

	private void checkIsInt(Object object, String fieldName, long min, long max) {
		long value = 0;
		if (object instanceof Long) {
			value = (Long)object;
		} else if (object instanceof Integer) {
			value = (Integer)object;
		} else if (object instanceof Short) {
			value = (Short)object;
		} else {
			throw new CustomException(fieldName + " is not exist or it is not a number");
		}
		
		if (value < min || value > max) {
			throw new CustomException(fieldName + " is out of range, min:" + min + ", max:" + max);
		}
	}
	
	private void checkIsString(Object object, String fieldName, int min, int max) {
		if (object  instanceof String) {
			String value = (String) object;
			value = value.trim();
			int size = value.length();
			if (size < min || size > max) {
				throw new CustomException(fieldName + " length is out of range, min:" + min + ", max:" + max);
			}
		} else {
			throw new CustomException(fieldName + " is not exist or it is not a string");
		}
	}
	
	private void checkInInt(Object object, String fieldName, long[] intList) {
		long value = 0;
		if (object instanceof Long) {
			value = (Long)object;
		} else if (object instanceof Integer) {
			value = (Integer)object;
		} else if (object instanceof Short) {
			value = (Short)object;
		} else {
			throw new CustomException(fieldName + " is not exist or it is not a number");
		}

		Arrays.sort(intList);
		if (Arrays.binarySearch(intList, value) < 0) {
			throw new CustomException(fieldName + " is not in array:" + Arrays.toString(intList));
		}
	}

	public enum VerifyType {		
		all, field, partial, none
	}
	
	/**
	 * 检查页数, 0 < page < 2000 && 0 < pageSize < 100
	 */
	public void checkPage(int page, int pageSize) {
		if (page <= 0 || page > 2000 || pageSize <= 0 || pageSize > 100) {
			throw new CustomException("page or page size is invalid");
		}
	}

}