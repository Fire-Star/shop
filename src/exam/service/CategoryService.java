package exam.service;

import java.util.LinkedList;

import exam.domain.CategoryItem;
import exam.domain.Categorys;
import exam.mysql.dao.CategoryDao;

public class CategoryService {
	private static CategoryDao categoryDao=new CategoryDao();
	private Categorys categorys;
	private LinkedList<CategoryItem> firstCategory;
	private String secondCategory;
	
	/*
	 * 一般菜单的实现有三层。而这个类的实现也只是实现了三层目录结构。
	 * 个人感觉实现很鸡肋。每次要用的时候都得调用load方法来加载服务器数据。
	 * 不过还好，服务器只会访问一次。而且每次都要保留数据到内存，让访问更高效。
	 * 缺点。耗内存。每次传递的CategoryItem太大。
	 */
	public void loadCategory(){
		firstCategory=null;
		Categorys categorys=new Categorys();
		LinkedList<CategoryItem> categoryItems=categoryDao.loadCategorys();
		for(CategoryItem i:categoryItems){
			categorys.loadCategoryItem(i);
		}
		this.categorys=categorys;
	}
	
	public LinkedList<CategoryItem> getFirstCategory(){
		if(firstCategory!=null)return firstCategory;
		LinkedList<CategoryItem> content=new LinkedList<CategoryItem>();
		//在数据库中顶层目录的fathercode为0，因为Category这个获取后的Category具有数据库的数据目录结构，所以可以直接获取顶层为0，就可以得到所有顶层目录。
		LinkedList<CategoryItem> firstCategorys=categorys.getCategorys().get("0");
		this.firstCategory=firstCategorys;
		return firstCategorys;
	}
	
	
	public LinkedList<CategoryItem> getSecondCategory(String categoryCode){
		
		LinkedList<CategoryItem> firstCategory=getFirstCategory();
		CategoryItem aimCategory=null;
		
		for(CategoryItem items:firstCategory){
			if(items.getCode().equals(categoryCode)){
				aimCategory=items;
			}
		}
		
		LinkedList<CategoryItem> secondeCategory=aimCategory.getCategoryNext().get(aimCategory.getCode());

		return secondeCategory;
	}
	
	public LinkedList<CategoryItem> getSecondCategory(){
		return getSecondCategory(secondCategory);
	}
	
	public LinkedList<CategoryItem> getThirdCategory(String firstCategoryCode,String secondCategoryCode){
		
		LinkedList<CategoryItem> secondeCategory=getSecondCategory(firstCategoryCode);
		CategoryItem aimCategory=null;
		
		for(CategoryItem items:secondeCategory){
			if(items.getCode().equals(secondCategoryCode)){
				aimCategory=items;
			}
		}
		
		LinkedList<CategoryItem> thirdCategory=aimCategory.getCategoryNext().get(aimCategory.getCode());
		return thirdCategory;
	}

	public void setSecondCategory(String secondCategory) {
		this.secondCategory = secondCategory;
	}
	public CategoryItem getCategoryItemsByFatherCode(String fatherCode){
		CategoryItem categoryItems=categoryDao.getCategoryItemsByFatherCode(fatherCode);
		return  categoryItems;
	}
}
