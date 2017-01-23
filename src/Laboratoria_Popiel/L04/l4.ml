
(* TASK 1 *)

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

let tree = put ("Czerwony","Red") Empty;;
let tree = put ("Niebieski","Blue") tree;;
let tree = put ("Bialy","White") tree;;
let tree = put ("Zielony","Green") tree;;
let tree = put ("Czarny","Black") tree;;
let tree = put ("Zolty","Yellow") tree;;
let tree = put ("Rozowy","Pink") tree;;

english "Bordowy" tree;;
english "Zielony" tree;;


(* TASK 2 *)

type 'a bt = Empty | Node of 'a * 'a bt * 'a bt;;

let rec put elem = function 
	| Empty -> Node(elem,Empty,Empty)
	| Node(obj,left,right) as node -> 
		if elem < obj then Node(obj,put elem left,right)
		else if elem > obj then Node(obj,left,put elem right)
		else node;;

let rec higher = function
	| Empty -> raise(Failure "empty tree")
	| Node(obj, left, right) -> if right = Empty then obj else higher right;; 

let rec lowest = function
	| Empty -> raise(Failure "empty tree")
	| Node(obj, left, right) -> if left = Empty then obj else lowest left;; 

let rec delete elem = function
	| Empty -> Empty
	| Node(obj,left,right) as node -> 
		if elem = obj then match node with
		| Node(obj,Empty,Empty) -> Empty
		| Node(obj,Empty,right) -> right
		| Node(obj,left,Empty) -> left
		| Node(obj,left,right) -> Node(higher left, delete (higher left) left, right)
		else if elem < obj then Node(obj,delete elem left,right) else Node(obj,left,delete elem right);;		
		
let rec get elem = function
	| Empty -> raise(Failure "elen dont exists")
	| Node(obj, left, right) -> 
		if obj = elem then elem 
		else if elem < obj then get elem left
		else get elem right;;

let rec treeFromList list = match list with
	| [] -> Empty
	| h::tl -> put h (treeFromList tl);;

let rec printTree tree = match tree with
	| Empty -> "[]"
	| Node(obj,left,right) -> "( '"^obj^"' L: "^printTree left^" R: "^printTree right^" )";;
	
let wordTree = treeFromList ["ala";"dom";"daria";"ser";"michal";"para";"zebra";"hi"];;
printTree wordTree;;

put "new" wordTree;;
let wordTree = delete "hi" wordTree;;
get "daria" wordTree;;

lowest wordTree;;


