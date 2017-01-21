(* Task 1 *)

let rec flatten list =
  if list=[] then [] else List.hd list@flatten(List.tl list);;

(* Task 2 *)

let rec count a  =
  if(snd a=[]) then 0
  else (if List.hd(snd a)=fst a then 1 else 0) + count (fst a,List.tl(snd a));;

(* Task 3 *)

let rec replicate a =
  if snd a=0 then []
  else if snd a>0 then [fst a]@replicate(fst a,(snd a)-1)
  else raise (Failure "invalid replicate number");;

  replicate("Dominik",4);;
  replicate(2.0,6);;

(* Task 4 *)
    
  let rec sqrList list =
    if list=[] then []
    else [List.hd list*List.hd list]@sqrList(List.tl list);;

  sqrList([2;4;8;6]);;

(* Task 5 *)      

  let palindrome list = list = List.rev list;;

    palindrome [1;2;3];;
    palindrome [2;3;2];;

(* Task 6 *)

  let rec listLength list =
    if list=[] then 0 else 1+listLength(List.tl list);;

    listLength [1;2;3;4;5];;
    listLength [10];;
    listLength [];;		    
