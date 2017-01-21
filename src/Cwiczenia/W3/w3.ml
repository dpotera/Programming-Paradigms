
(* TASK 1 *)
 let f1 x = x 1 1;;
(int -> int -> 'a) -> 'a
let f1 = function x -> x 1 1;;

let f2 x y z = x (y ^ z);;
(string -> 'a) -> string -> string -> 'a

let f2 = function x -> function y -> function z -> x (y ^ z);;


(* TASK 2 *)

let curry3 f x y z = f (x,y,z);;

let uncurry3 f (x,y,z) = f x y z;;

(* TASK 3 *)

let rec sumProd l =
  match l with
  h::t -> let (s,p) = sumProd t
  				in (h+s,h*p)
  | [] -> (0,1);;


let sumProdF = List.fold_left(fun (x,y) k -> (x+k,y*k))(0,1);;

(* TASK 4 *)

let rec quicksort = function
	| [] -> []
	| [x] -> [x]
	| xs ->
			let small = List.filter (fun y -> y < List.hd xs ) xs
			and large = List.filter (fun y -> y >= List.hd xs ) xs		(* Zapetlanie sie, pierwszy elelement nie jest usuwany *)
			in quicksort small @ quicksort large

let rec quicksort' = function
	| [] -> []
	| x :: xs ->
			let small = List.filter (fun y -> y < x ) xs
			and large = List.filter (fun y -> y > x ) xs				(* Usuwa powtarzajace sie elementy - wystarczy dodac = *)
			in quicksort' small @ (x :: quicksort' large)


quicksort [4;5;2;3;7;6;9;1;2;8;4;2;1;0;6;2;8;3;5];;
