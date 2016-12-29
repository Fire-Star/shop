package exam.domain;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Categorys {
	private Map<String, LinkedList<CategoryItem>> categorys=new HashMap<String, LinkedList<CategoryItem>>();
	private boolean flag;
	PrintStream printStream;
	
	public void loadCategoryItem(CategoryItem item){
		flag=true;
		loadNextCategoryItem(categorys, item);
		if(flag){
			LinkedList<CategoryItem> categoryItems=new LinkedList<CategoryItem>();
			categoryItems.add(item);
			categorys.put(item.getFathercode(), categoryItems);
		}
	}
	
	public Map<String, LinkedList<CategoryItem>> getCategorys(){
		return categorys;
	}
	
	private void loadNextCategoryItem(Map<String, LinkedList<CategoryItem>> currentCategory,CategoryItem item){
		if(flag==false)return;
		LinkedList<CategoryItem> items=currentCategory.get(item.getFathercode());
		if(items==null){
			Set<String> keys=currentCategory.keySet();
			for(String key:keys){
				LinkedList<CategoryItem> next=currentCategory.get(key);
				for(CategoryItem cateItem:next){
					Map<String, LinkedList<CategoryItem>> nextCategoryItem=cateItem.getCategoryNext();
					if(cateItem.getCode().equals(item.getFathercode())){
						LinkedList<CategoryItem> newlinkedCategoryItems=cateItem.getCategoryNext().get(cateItem.getCode());
						if(newlinkedCategoryItems==null)
							newlinkedCategoryItems=new LinkedList<CategoryItem>();
						
						newlinkedCategoryItems.add(item);
						nextCategoryItem.put(cateItem.getCode(), newlinkedCategoryItems);
						
						flag=false;
						return;
					}
					loadNextCategoryItem(nextCategoryItem, item);
				}
			}
		}else {
			flag=false;
			items.add(item);
		}
	}
}
