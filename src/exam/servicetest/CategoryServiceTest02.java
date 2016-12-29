package exam.servicetest;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import exam.service.CategoryService;

public class CategoryServiceTest02 {
	public static void main(String[] args) throws IntrospectionException {
		BeanInfo beanInfo = Introspector.getBeanInfo(CategoryService.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for(int i=0;i<pds.length;i++){
            System.out.println(pds[i].getName());
        }
	}
}
