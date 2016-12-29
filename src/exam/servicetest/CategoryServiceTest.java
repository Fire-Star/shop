package exam.servicetest;

import java.util.LinkedList;

import exam.domain.CategoryItem;
import exam.service.CategoryService;

public class CategoryServiceTest {
//	public static void main(String[] args) {
//		System.out.println("+++++++++");
//		CategoryService categoryService=new CategoryService();
//		categoryService.loadCategory();
//		
//	}
//	public static void main(String[] args) {
//		CategoryService categoryService=new CategoryService();
//		categoryService.loadCategory();
//		for(CategoryItem i:categoryService.getFirstCategory()){
//			System.out.println(categoryService.getFirstCategory().size());
//			System.out.println("******************************************************");
//			System.out.println("#######"+i.getName()+"#######");
//			for(CategoryItem s:categoryService.getSecondCategory(i.getCode())){
//				System.out.println("@@@@@@@"+s.getName()+"@@@@@@@");
//				for(CategoryItem t:categoryService.getThirdCategory(i.getCode(),s.getCode())){
//					System.out.println("-------"+t.getName()+"-------");
//				}
//			}
//			System.out.println("******************************************************");
//		}
//	}
	public static void main(String[] args) {
		CategoryService categoryService=new CategoryService();
		categoryService.loadCategory();
		
		LinkedList<CategoryItem> first=categoryService.getFirstCategory();
		for(int i=0;i<first.size();i++){
			CategoryItem f=first.get(i);
			System.out.println("******************************************************");
			System.out.println("#######"+f.getName()+"#######");
			LinkedList<CategoryItem> second=f.getCategoryNext().get(f.getCode());
			for(int s=0;s<second.size();s++){
				CategoryItem se=second.get(s);
				System.out.println("@@@@@@@"+se.getName()+"@@@@@@@");
				LinkedList<CategoryItem> third=se.getCategoryNext().get(se.getCode());
				for(int t=0;t<third.size();t++){
					CategoryItem th=third.get(t);
					System.out.println("-------"+th.getName()+"-------");
				}
			}
			
			System.out.println("******************************************************");
		}
		
	}
}
