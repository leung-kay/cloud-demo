package com.ruifucredit.cloud.kit.po;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

public class PoKit {

	/**
	 * <p>合并同一个类两个对象的属性，将第一个对象和第二个对象的属性复制到第三个对象中</p>
	 * <p>如果两个对象中都有同一个属性，则使用第一个对象的属性值</p>
	 * @param from 第一个对象
	 * @param to 第二个对象
	 * @param <T> 两个对象必须同属一个类
	 * @return 方法内部创建的，用于存放两个对象属性的第三个对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T join(T from, T to) {

		Object result = BeanUtils.instantiate(to.getClass());

		if (to != null) {
			BeanUtils.copyProperties(to, result, nullNames(to));
		}

		if (from != null) {
			BeanUtils.copyProperties(from, result, nullNames(from));
		}

		return (T) result;
	}

	private static <T> String[] nullNames(T t) {

		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(t.getClass());

		Set<String> names = new HashSet<>();

		for (PropertyDescriptor pd : pds) {

			Object value = ReflectionUtils.invokeMethod(pd.getReadMethod(), t);

			if (value == null)
				names.add(pd.getName());

		}

		return names.toArray(new String[] {});

	}

}
