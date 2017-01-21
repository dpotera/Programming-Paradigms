

module type PQueue = 
	sig
			type 'a queue
			exception QueueEmpty of string
			exception QueueFull of string
			val create: unit -> 'a queue
			val enqueue: 'a queue -> 'a -> int -> unit
			val top: 'a queue -> 'a * int
			val pop: 'a queue -> 'a * int
			val merge: 'a queue -> 'a queue -> 'a queue
	end;;
 
module PriorityQueue : PQueue = 
	struct
		type 'a queue = {mutable list:('a * int) list; mutable left:int} 
		exception QueueEmpty of string
		exception QueueFull of string
		
		let create() = {list=[]; left=100}
		
		let enqueue que value weight = 
			if que.left = 0 then raise (QueueFull "Queue is Full.")
			else match que.list with
			| [] -> (que.list <- [(value,weight)]; que.left <- 99)
			| list ->  let rec add lst v w =
					match lst with
					| [] -> [(v,w)]
					| h::t as l -> if (snd h) <= w then (v,w)::l
					  		else h::(add t v w)
			  in (que.list <- (add list value weight); que.left <- que.left-1);;
		
		let top que = 
			if que.list = [] then raise (QueueEmpty "Queue is Empty.")
			else List.hd que.list;;

		let pop que = match que.list with
			| [] -> raise (QueueEmpty "Queue is Empty.")
			| h::t -> (que.list <- (List.tl que.list); h);;

		let merge que1 que2 = 
			let rec addList que = function
					| [] -> ()
					| (v,w)::t -> (enqueue que v w; addList que t)
			and que = create()
		  in (
				que.left <- que1.left + que2.left;
   			addList que que1.list;
				addList que que2.list;
				que
			);;

	end;;

let q = PriorityQueue.create();
PriorityQueue.enqueue q 1 10;;
PriorityQueue.enqueue q 2 9;;
PriorityQueue.enqueue q 3 8;;
PriorityQueue.enqueue q 4 7;;
PriorityQueue.enqueue q 5 6;;

let w = PriorityQueue.create();
PriorityQueue.enqueue w 6 5;;
PriorityQueue.enqueue w 7 4;;
PriorityQueue.enqueue w 8 3;;
PriorityQueue.enqueue w 9 2;;
PriorityQueue.enqueue w 10 1;;

PriorityQueue.top q;;
PriorityQueue.pop q;;

PriorityQueue.top w;;
PriorityQueue.pop w;;

let merge = PriorityQueue.merge q w;
PriorityQueue.top merge;;
PriorityQueue.pop merge;;

