package homeposting.app.common.proc.pagination;

import homeposting.app.domain.entities.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PaginationContext {
	private int elementsCount;
	private int elementsPerPage;
	private int currPage;
	private QueryElementsSource query;
	private List<Transaction> elements;
	
	public PaginationContext(QueryElementsSource query){
		setQuery(query);
	}
	//====================================
	public List<Transaction> getElements(){
		if(elements == null){
			elements = query.getElements(currPage * elementsPerPage,elementsPerPage, false);
		}
		return elements;
	}
	private void setDirty() {
		elements = null;
	}
	public int getCountPages() {
		return (elementsCount + elementsPerPage -1)/elementsPerPage;
	}
	//====================================
	public boolean setQuery(QueryElementsSource query) {
		if(this.query != query){
			this.query = query;
			reset();
			return true;
		}
		return false;
	}
	public void reset() {
		elementsCount = (int)query.getCount();
		setElementsPerPage(30);
		currPage = elementsCount != 0 ? 0 : -1;
		setDirty();
	}
	public boolean setElementsPerPage(int count) {
		if(count > 0){
			elementsPerPage = count;
			return setPage(0);
		}
		return false;
	}
	public boolean previous(){
		if(currPage > 0){
			return setPage(currPage-1);
		}
		return false;
	}
	public boolean next(){
		if(currPage < getCountPages()-1){
			return setPage(currPage+1);
		}
		return false;
	}
	public boolean last(){
		int newCurrPage = getCountPages()-1;
		if(currPage != newCurrPage){
			return setPage(newCurrPage);
		}
		return false;
	}
	public boolean first(){
		if(currPage > 0){
			return setPage(elementsCount != 0 ? 0 : -1);
		}
		return false;
	}
	public boolean setPage(int ind){
		if(currPage != ind && ind < getCountPages() && ind > -1){
			if(elementsCount > 0){
				currPage = ind;
				setDirty();
				return true;
			}
		}
		return false;
	}
	
	public List<Integer> getNavigation() {
		//========
		List<Integer> list = new ArrayList<Integer>(getSetNavigation());
		Collections.sort(list);
		int ind = list.indexOf(currPage);
		if(ind > -1){
			list.set(ind, -1 - currPage);
		}
		return list;
	}
	private Set<Integer> getSetNavigation() {
		//znalezienie punktów pośrednich
		int lindex = getCountPages();
		if(lindex == 0){
			return Collections.EMPTY_SET;
		}
		lindex--;
		//==============
		Set<Integer> set = new HashSet<Integer>();
		int temp = Math.min(2, lindex);
		for(int x = 0; x <= temp ; x++){
			set.add(x);
		}
		if(lindex < 3){
			return set;
		}
		for(int x = lindex-2; x <= lindex ; x++){
			set.add(x);
		}
		//================
		int b = 3;
		int ml = currPage - 2;
		int mr = currPage + 2;
		int e = lindex - 3;
		if(ml > b+2){
			temp = 1;
			while(b < ml){
				set.add(b);
				set.add(ml);
				temp += temp;
				b += temp;
				ml -= temp;
			}
		}
		if(e > mr+2){
			temp = 1;
			while(mr < e){
				set.add(mr);
				set.add(e);
				temp += temp;
				mr += temp;
				e -= temp;
			}
		}
		if(currPage  > 3 && currPage < lindex -2){
			set.add(currPage);
			set.add(currPage-1);
			set.add(currPage+1);
		}
		return set;
	}
	public boolean isFirstEnabled() {
		return currPage > 0;
	}
	public boolean isLastEnabled() {
		return currPage < getCountPages() -1;
	}
}
