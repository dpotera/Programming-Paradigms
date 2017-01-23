

module type PQueue = 
	sig
			type 'a queue
			exception QueueEmpty of string
			exception QueueFull of string
			val create: 'a queue
			val enqueue: 'a queue -> 'a -> int -> 'a queue
			val top: 'a queue -> 'a * int
			val pop: 'a queue -> 'a queue
			val merge: 'a queue -> 'a queue -> 'a queue
	end;;
 
module PriorityQueue : PQueue = 
	struct
		type 'a queue = Empty | Node of ('a * int) list * int
		exception QueueEmpty of string
		exception QueueFull of string
		
		let create = Empty;;
		
		let enqueue que value weight = 
			match que with
			| Empty -> Node([(value,weight)],99) 
			| Node(_,0) -> raise (QueueFull "Queue is Full.")
			| Node(list,left) -> 
				let rec add lst v w =
					match lst with
					| [] -> [(v,w)]
					| h::t as l -> if (snd h) <= w then (v,w)::l
					  		else h::(add t v w)
			  in Node((add list value weight),left-1);;
		
		let top = function
			| Empty -> raise (QueueEmpty "Queue is Empty.")
			| Node(list,_) -> List.hd list;;

		let pop = function
			| Empty -> raise (QueueEmpty "Queue is Empty.")
			| Node(list,left) -> Node(List.tl list,left+1);;

		let merge que1 que2 = match (que1,que2) with
			| (Empty,Empty) -> Empty
			| (q1,Empty) -> q1
			| (Empty,q2) -> q2
			| (Node(lst1,lft1),Node(lst2,lft2)) -> 
				let rec addList que = function
					| [] -> que
					| (v,w)::t -> addList (enqueue que v w) t
				in addList (addList (Node([],lft1+lft2)) lst1) lst2;;

	end;;


let q = PriorityQueue.create;;
let q = PriorityQueue.enqueue q 2 3;;
let q = PriorityQueue.enqueue q 5 1;;
let q = PriorityQueue.enqueue q 6 4;;
let q = PriorityQueue.enqueue q 9 2;;
let q = PriorityQueue.enqueue q 7 1;;
PriorityQueue.top q;;



