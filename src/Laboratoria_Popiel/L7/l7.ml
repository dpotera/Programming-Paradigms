
(* TASK 1 *)

let swap tab i j =
	let aux = tab.(i) in tab.(i) <- tab.(j); tab.(j) <- aux;;

let bubbleSort array =
	for i=1 to (Array.length array)-1 do
		for j=0 to (Array.length array)-i-1 do
			 if array.(j) > array.(j+1) then swap array j (j+1)
		done
	done;;

let array = [|4;3;0;5;1;2|];;
array;;
bubbleSort array;;
array;;

(* TASK 2 *)

type 'a bt = Empty | Node of 'a * 'a bt * 'a bt;;

let rec put elem tree = match tree with
	| Empty -> Node(elem,Empty,Empty)
	| Node((pl,eng),left,right) as node -> 
		if fst elem > pl then Node((pl,eng),left,put elem right)
		else if fst elem < pl then Node((pl,eng),put elem left,right)
		else node;;

let rec english pol bt = match bt with
  | Empty -> raise(Failure "element not found")
	| Node((pl,eng),left,right) -> 
		if pl = pol then eng
		else if pol < pl then english pol left
		else english pol right;;

let addWord() tree = print_string "Slowo po polsku: ";
  let pl = read_line() in
  print_string "Slowo po angielsku: ";
	put (pl,read_line()) tree;;

let findWord() tree = print_string "Slowo po polsku do przetlumaczenia: ";
  let pl = read_line() in
	english pl tree;;

let tree = put ("mama;;","mom") Empty;;
let tree = addWord() tree;;

let interface() = 
	print_endline "1. Dodaj slowo\n2. Wyszukaj slowo";
	match read_line() with
	| "1;;" -> let tmp = (addWord() tree) in ()
	| "2;;" -> print_string (findWord() tree);
	| _ -> print_string "invalid option";;

interface();;

