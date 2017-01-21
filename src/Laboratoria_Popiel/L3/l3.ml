
(* TASK 1 *)

let rec nwd a b = if b = 0 then a else nwd b (a mod b);;

let coprime nwd a b = nwd a b = 1;;

nwd 867 1989;;
nwd 1989 867;;

coprime nwd 6 35;;


(* TASK 2 *)

let rec eulerList n i list =
	if i=0 then list
	else if coprime nwd n i then eulerList n (i-1) (i::list)
	else eulerList n (i-1) list;;

let euler eulerList n = eulerList n n [];;

euler eulerList 21;;