package com.hevo.study.app.test;

import org.apache.commons.lang3.StringUtils;

public class TestCase {

	public static void main(String[] args) {
		
		System.out.println(StringUtils.isEmpty(""));
		System.out.println(StringUtils.isEmpty("  "));
		System.out.println(StringUtils.isEmpty(null));

	}

}
