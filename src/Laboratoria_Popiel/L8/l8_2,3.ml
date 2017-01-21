

module type HeapSig = 
	sig
		type 'a heap = {mutable array: 'a array; mutable size:int}
		exception HeapEmpty of string
		val create: int -> int heap
		val insert: 'a heap -> 'a -> unit
		val pop: 'a heap -> 'a
		val heapSort: int array -> unit
	end;;

module Heap : HeapSig = 
	struct
		type 'a heap = {mutable array: 'a array; mutable size:int}
	  exception HeapEmpty of string		
		
		let create heapSize = {array = Array.make heapSize 0; size = 0 }
			
    let swap tab i j =
    	let aux = tab.(i) in tab.(i) <- tab.(j); tab.(j) <- aux;;
		
		let insert heap value = 
			begin  			
				let index = ref heap.size in
				heap.array.(!index) <- value;
				while !index > 0 && heap.array.(!index) > heap.array.(!index/2) do
					swap heap.array !index (!index/2);
					index := !index/2
					done;
				heap.size <- heap.size+1
			end;;

		let pop heap = 
			if heap.size = 0 then raise (HeapEmpty "Heap is Empty.")
			else 
				begin
    			let rec sink i =
    				if 2*i < heap.size && heap.array.(i) < heap.array.(2*i) then 
    					(swap heap.array i (2*i); sink (2*i));
    				if 2*i+1 < heap.size && heap.array.(i) < heap.array.(2*i+1) then
    					(swap heap.array i (2*i+1); sink (2*i+1))
					and top = heap.array.(0) in 					
    			heap.array.(0) <- heap.array.(heap.size-1);
    			heap.size <- heap.size - 1;
    			sink 0;
					top
				end;;

    let heapSort arr = 
    	let heap = create (Array.length arr) and
			len = ref (Array.length arr) in
    	begin
      	for i=0 to (!len-1) do
      		insert heap arr.(i)
      	done;
      	while !len > 0 do 
    			arr.(!len-1) <- pop heap;
					len := !len-1
    		done
    	end;;

	end;;

let heap = Heap.create 10;;
Heap.insert heap 2;;
Heap.insert heap 3;;
Heap.insert heap 54;;
Heap.insert heap 112;;
heap;;
Heap.pop heap;;
tab.(0) <- 2;;

let tab = Array.make ((Array.length tab) + 1) tab;;


let array = [|4;7;3;6;8;5;1;2|];;
Heap.heapSort array;;
array;;





