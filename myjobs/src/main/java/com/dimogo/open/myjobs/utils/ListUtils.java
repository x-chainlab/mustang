package com.dimogo.open.myjobs.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan Xiao on 2017/4/19.
 */
public class ListUtils {

	public static <T> List<T> subList(List<T> list, int startIndex, int toIndex) {
		if (list == null || list.size() == 0) {
			return list;
		}
		int endIndex = list.size();
		if (endIndex <= startIndex) {
			return new ArrayList<T>(0);
		}
		if (endIndex < toIndex) {
			return list.subList(startIndex, endIndex);
		}
		return list.subList(startIndex, toIndex);
	}

}
