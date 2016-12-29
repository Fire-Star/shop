package exam.domain;

import java.util.LinkedList;
import java.util.Map;

public class CategoryItem {
	private String code;
	private String name;
	private String fathercode;
	
	private Map<String, LinkedList<CategoryItem>> categoryNext;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFathercode() {
		return fathercode;
	}
	public void setFathercode(String fathercode) {
		this.fathercode = fathercode;
	}
	public CategoryItem() {
		super();
	}
	public CategoryItem(String code, String name, String fathercode, Map<String, LinkedList<CategoryItem>> categoryNext) {
		super();
		this.code = code;
		this.name = name;
		this.fathercode = fathercode;
		this.categoryNext = categoryNext;
	}
	public Map<String, LinkedList<CategoryItem>> getCategoryNext() {
		return categoryNext;
	}
	public void setCategoryNext(Map<String, LinkedList<CategoryItem>> categoryNext) {
		this.categoryNext = categoryNext;
	}
	@Override
	public String toString() {
		return "CategoryItem [code=" + code + ", name=" + name + ", fathercode=" + fathercode + ", categoryNext="
				+ categoryNext + "]";
	}
}
