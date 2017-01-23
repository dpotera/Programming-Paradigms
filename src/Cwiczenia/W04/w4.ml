

(* TASK 1 *)

let f1 x y z = x y z;;
val f1 : ('a -> 'b -> 'c) -> 'a -> 'b -> 'c = <fun>

let f1 = function x -> function y -> function z -> x y z;;

let f2 x y = function z -> x::y;;
val f2 : 'a -> 'a list -> 'b -> 'a list = <fun>

let f2 = function x -> function y -> function z -> x::y;;

(* TASK 2 *)
let rec f x = f x;;

(* TASK 3 *)
type 'a bt = Empty | Node of 'a * 'a bt * 'a bt;

let tree = 
	Node(1, 
		Node(2, 
			Node(4,Empty,Empty), 
			Empty), 
		Node(3, 
			Node(5, Empty, 
				Node(6,Empty,Empty)), 
			Empty));;

let breadthBT t =
	let rec breadth = function 
		| [] -> []
		| Empty::tl -> breadth tl
		| Node(v, l, r)::tl -> v::breadth(tl @ (l::r::[]))
	in breadth [t];;

breadthBT tree;;

(* TASK 4 *)

let internal t =
  let rec inter t acc = match t with
  	| Empty -> failwith "empty tree"
  	| Node(v,Empty,Empty) -> acc
  	| Node(v,l,Empty) -> acc + (inter l (acc+1))
  	| Node(v,Empty,r) -> acc + (inter r (acc+1))
  	| Node(v,r,l) -> acc + (inter r (acc+1)) + (inter l (acc+1))
	in inter t 0;;

internal tree;;

let externalBT t =
	let rec ext t acc = match t with
		| Empty -> acc
		| Node(_,Empty,Empty) -> 2*(acc+1)
		| Node(_,l,Empty) -> acc+1 + (ext l (acc+1))
		| Node(_,Empty,r) -> acc+1 + (ext r (acc+1))
		| Node(_,l,r) -> (ext l (acc+1)) + (ext r (acc+1))
	in ext t 0;;

externalBT tree;;

(* TASK 5 *)

type 'a graph = Graph of ('a -> 'a list);;

let g = Graph(function
	| 0 -> [3]
	| 1 -> [0;2;4]
	| 2 -> [1]
	| 3 -> []
	| 4 -> [0;2]
	| n -> failwith("node "^string_of_int n^" dont exist")
	);;


let breadthSearch (Graph succ) startNode =
  let rec search visited = function
    [] -> []
    | h::t -> if List.mem h visited then search visited t
    else h::search (h::visited) (t @ succ h)
  in search [] [startNode];;

let depthSearch (Graph succ) n =
  let rec search visited = function
    [] -> []
    | h::t -> if List.mem h visited then search visited t
    else h::search (h::visited) (succ h @ t)
  in search [] [n];;


depthSearch g 4;;




