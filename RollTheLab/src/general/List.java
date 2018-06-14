package general;

import labImport.ImagePoint;

public class List<T> {
	private int count = 0;
	private ListEl first = null;
	private ListEl last = null;
	
	
	public List() {	}
	
//	public List(List<T> list) {
//		this.count = list.count;
//		this.first = 
//	}
	
	//Getter and Setter
	public int getCount() { return count;}
	public ListEl getFirst() { return first;}
	public ListEl getLast() { return last;}
	
	//Adds a new element at the end of the list
	public ListEl Add(T newEl) {
		ListEl newLEl = new ListEl(newEl, null, last, ++count);
		if(first == null)
			first = newLEl;
		else
			last.nextEl = newLEl;
		last = newLEl;
		return last;
	}
	
	public class ListEl
	{
		private ListEl(T el, ListEl next, ListEl prev, int index) {
			element = el;
			nextEl = next;
			prevEl = prev;
			this.index = index;			
		}
		public T element;
		private ListEl nextEl;
		private ListEl prevEl;
		private int index;
		
		public ListEl getNext() { return nextEl;}
		public ListEl getPrev() { return prevEl;}
		public int getIndex() { return index; }
	}

	//removes one element from the List
	public void removeEl(ListEl curFoundEl) {
		if(curFoundEl.prevEl != null)
			curFoundEl.prevEl.nextEl = curFoundEl.nextEl;
		if(curFoundEl.nextEl != null)
		{
			curFoundEl.nextEl.prevEl = curFoundEl.prevEl;
			curFoundEl.nextEl.index--;
		}
		if(curFoundEl.prevEl == null)
			first = curFoundEl.nextEl;
		if(curFoundEl.nextEl == null)
			last = curFoundEl.prevEl;
		count--;
		curFoundEl.prevEl = null;
		curFoundEl.nextEl = null;
	}
	
}
