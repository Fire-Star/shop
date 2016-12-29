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
	 * һ��˵���ʵ�������㡣��������ʵ��Ҳֻ��ʵ��������Ŀ¼�ṹ��
	 * ���˸о�ʵ�ֺܼ��ߡ�ÿ��Ҫ�õ�ʱ�򶼵õ���load���������ط��������ݡ�
	 * �������ã�������ֻ�����һ�Ρ�����ÿ�ζ�Ҫ�������ݵ��ڴ棬�÷��ʸ���Ч��
	 * ȱ�㡣���ڴ档ÿ�δ��ݵ�CategoryItem̫��
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
		//�����ݿ��ж���Ŀ¼��fathercodeΪ0����ΪCategory�����ȡ���Category�������ݿ������Ŀ¼�ṹ�����Կ���ֱ�ӻ�ȡ����Ϊ0���Ϳ��Եõ����ж���Ŀ¼��
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
