package org.liko.event.tool;

import com.iquantex.generator.MybatisBeanDaoGenerator;

public class Generator {
	public static void main(String []args) {
		try {
			MybatisBeanDaoGenerator.generator();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
