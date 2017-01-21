
(* ZADANIE 1 *)

let compress l =
	let rec comp l prev acc = match l with
	| [] -> acc
	| h::t -> if(h<>prev) then comp t h (acc@[h]) else comp t h acc
	in comp (List.tl l) (List.hd l) [List.hd l];;

compress [1;1;1;5;5;5;2;2;2;2;2;3;3;3;3;4;4];;

(* ZADANIE 4 *)

let rec lloop = function LNil -> []
	| LCons(v,f) -> v :: (lloop @@ f());;

type 'a bt = Empty | Node of 'a * 'a bt * 'a bt;

type 'a llist = LNil | LCons of 'a * (unit -> 'a llist);;

let tree = 
	Node(4, 
		Node(2, 
			Node(1,Empty,Empty), 
			Empty), 
		Node(8, 
			Node(5, Empty, 
				Node(6,Empty,Empty)), 
			Empty));;

let rec (@$) ll1 ll2 = match ll1 with
	| LNil -> ll2
	| LCons(x, xf) -> LCons(x, function () -> (xf()) @$ ll2);; 

let rec lazyInOrder tree = match tree with
	| Empty -> LNil 
	| Node(v,l,r) -> lazyInOrder l @$ LCons(v,function () -> lazyInOrder r);;

lazyInOrder tree;;
lloop @@ lazyInOrder tree;;

