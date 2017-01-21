

let rec toLazyList = function
	| [] -> LNil
	| h::t -> LCons(h, function() -> toLazyList t);;

let rec flatten = function
	| [] -> LNil
	| (h::[])::tl -> LCons(h,function() -> flatten tl)
	| (h::t)::tl -> LCons(h,function() -> flatten @@ t::tl);;

(* TASK 1 *)

type 'a llist = LNil | LCons of 'a * (unit -> 'a llist);;

type 'a elem = A of 'a | AList of 'a list;;
type 'a mylist = 'a elem list;;

let rec flatten = function
	| [] -> []
	| A a::t -> a::(flatten t)
	| AList list::t -> list@(flatten t);;

flatten [A(1); AList([2; 3; 4]); AList([5; 6]); A(7)];;



(* TASK 2 *)

let rec lloop = function LNil -> []
	| LCons(v,f) -> v :: (lloop @@ f());;

let rec filter pred = function 
	| [] -> []
	| h::t -> if pred h then h::(filter pred t) else filter pred t;;

let rec group = function 
	| [] -> LNil
	| h::t as list -> LCons(filter (function x -> x=h) list,
										function() -> group @@ filter (function x -> x<>h) list);;

let grpd = group ['x';'a';'b';'a';'b';'c';'d';'a'];;
lloop grpd;;


