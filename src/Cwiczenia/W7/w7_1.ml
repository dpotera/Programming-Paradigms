module type QUEUE_FUN =
  sig
    type 'a t
    exception Empty of string
    val empty: unit -> 'a t
    val enqueue: 'a * 'a t -> 'a t
    val dequeue: 'a t -> 'a t
    val first: 'a t -> 'a
    val isEmpty: 'a t -> bool
  end;;

module Queue : QUEUE_FUN = 
	struct
		type 'a t = 'a list
		exception Empty of string
		let empty() = [];;
		let enqueue (v,q) = q@[v];;

		let dequeue = function
			| [] -> []
			| h::t -> t;;

		let first = function
			| [] -> raise (Empty "queue empty")
			| h::t -> h;;

		let isEmpty q = q = [];;
	end;;

module Queue : QUEUE_FUN = 
	struct
		type 'a t = 'a list * 'a list
		exception Empty of string
		let empty() = ([],[]);;
		
		let swap = function
			| ([],e) -> (List.rev e,[])
			| q -> q;;

		let enqueue (v,(x,y)) = swap (x,v::y);;

		let dequeue = function
			| ([],_) -> ([],[])
			| (h::t,e) -> swap (t,e);;

		let first = function
			| ([],_) -> raise (Empty "queue empty")
			| (h::t,e) -> h;;

		let isEmpty q = fst q = [];;
	end;;
