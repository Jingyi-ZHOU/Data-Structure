package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; // load this array
	private int size;
	private static final int arraySize = 10000; // Everything in the array will initially
												// be null. This is ok! Just build out
												// from array[1]

	public MinBinHeap() {
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(null, -100000); // 0th will be unused for simplicity
													// of child/parent computations...
													// the book/animation page both do this.
		size = 0;
	}

	// Please do not remove or modify this method! Used to test your entire Heap.
	@Override
	public EntryPair[] getHeap() {
		return this.array;
	}

	@Override
	public void insert(EntryPair entry) {
		array[++size] = entry;
		for(int j=size/2;j>0;j--) {
			this.bubbleDown(j);
		}

	}

	@Override
	public void delMin() {
		array[1]=array[size--];
		bubbleDown(1);
	}

	@Override
	public EntryPair getMin() {
		return array[1];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void build(EntryPair[] entries) {
		if (entries == null)
			return;
		for(int i=0;i<entries.length;i++){
			array[++size]=entries[i];
		}
		for(int j=size/2;j>0;j--) {
			this.bubbleDown(j);
		}
		

	}
	
	private void bubbleDown(int parent) {
		int child;
		EntryPair tmp = array[parent];
//		EntryPair tmp = new EntryPair(array[parent].getValue(),array[parent].getPriority());
		for(;parent*2<=size;parent=child) {
			child = parent*2;
			if(child != size && array[child].getPriority()>array[child+1].getPriority()) {
				child++;
			}
			if(array[parent].getPriority()>array[child].getPriority()) {
				array[parent] = array[child];
				array[child] = tmp;	
			}
		}
			
	}

}