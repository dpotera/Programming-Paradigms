
(* module Prim =
	struct *)
		
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
					val isEmpty: 'a queue -> bool
					val addList: 'a queue -> ('a * int) list -> unit
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
    					| h::t as l -> if (snd h) >= w then (v,w)::l
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

				let isEmpty q = q.left = 100;;
    
				let rec addList q list = match list with
					| [] -> ()
					| (v,w)::t -> (enqueue q v w; addList q t);; 
		
    	end;;
    
    module Graph = 
    	struct
    		type 'a graph = {
    			mutable edges: ('a * 'a * int) list; 
    			mutable vertexs: 'a list
    			}
    	
    		let create = {edges = []; vertexs = [] }
    		
    		let containsVertex g v = 
    			let rec cont v = function
    				| [] -> false
    				| h::t -> if h=v then true else cont v t
    			in cont v g.vertexs;;
    			
    		let rec addVertex g v =
    			if containsVertex g v = false then g.vertexs <- g.vertexs@[v];; 
    
    		let addEdge g a b weight = (
    			g.edges <- g.edges@[(a,b,weight)];
    			addVertex g a; addVertex g b);;
    
    		let getEdges g v =
    			let rec get res = function
    				| [] -> res
    				| (a,b,w)::t -> if a=v then get ((b,w)::res) t
    					else if b=v then get ((a,w)::res) t 
    					else get res t
    			in get [] g.edges;;

				let getRoot g = match g.vertexs with
  				| [] -> failwith "Empty Graph"
  				| h::t -> h;;
				
    	end;;
    
    module Tree =
    	struct
    		type 'a tree = {
					mutable nodes: 'a list;
					mutable edges: ('a * 'a * int) list; 
					mutable weight: int; 
					mutable size:int}
    		
    		let create = {nodes = []; edges = []; weight = 0; size = 0}
    		
      	let containsNode t n = 
    			let rec cont n = function
    				| [] -> false
    				| h::t -> if h=n then true else cont n t
    			in cont n t.nodes;;
    		
    		let addNode t v w =
    			if containsNode t v = false then 
    				begin
    					t.nodes <- t.nodes@[v]; 
    					t.weight <- t.weight+w; 
    					t.size <- t.size + 1
    				end;;

				let addEdge tree f t w = (addNode tree t w;
					tree.edges <- tree.edges@[(f,t,w)]);;
    		
				let rec containsAll t list = match list with
					| [] -> true
					| h::tl -> if containsNode t h then containsAll t tl else false;; 
				
    	end;;

	open PriorityQueue;;
	open Graph;;
	open Tree;;

	let prim g = 
		let queue = PriorityQueue.create() and
		tree = Tree.create in
		begin
			let root = Graph.getRoot g in
  		Tree.addNode tree root 0;
  		let rec primRec vertex ifadd =
  			begin
  				if ifadd then PriorityQueue.addList queue (Graph.getEdges g vertex);
					if not (Tree.containsAll tree g.vertexs) && not (PriorityQueue.isEmpty queue) then (
    				let (v,w) = PriorityQueue.pop queue in
    				if not (Tree.containsNode tree v) then 
    					(Tree.addEdge tree vertex v w; primRec v true)
						else primRec vertex false
					)
				end
			in (primRec root true; tree)
		end;;


(*  end;; *)

let g = 0;;

let g = Graph.create;;
Graph.addEdge g "a" "b" 2;;
Graph.addEdge g "c" "b" 4;;
Graph.addEdge g "a" "c" 6;;
Graph.addEdge g "a" "d" 7;;
Graph.addEdge g "c" "d" 1;;
Graph.addEdge g "d" "e" 3;;

prim g;;

g.vertexs;;

Graph.getRoot g;;

Graph.getEdges g "a";;

let t = Tree.create;;
Tree.containsAll t g.vertexs;;



