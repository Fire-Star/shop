package exam.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;


public class CommonUtils {
	public static Object populateUserBase(Object object,Map userBaseMap){
		
		try {
			BeanUtils.populate(object, userBaseMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return object;
	}
}
