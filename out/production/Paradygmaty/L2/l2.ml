
(* TASK 1 *)

let rec ifPart a b = match b with
  | "" -> false
  | c -> if a=c then true else ifPart a (String.sub c 1 ((String.length c) - 1));;

let ifPart a b =
  let rec help a al b n = match b with
    | "" -> n=al
    | str -> if n=al then true
	     else if (String.get str 0)=(String.get a n) then
	       help a al (String.sub str 1 ((String.length str) - 1)) (n+1)
	     else help a al (String.sub str 1 ((String.length str) - 1)) 0
  in help a (String.length a) b 0;;



  

  
let cutStr a = (String.sub a 1 ((String.length a) - 1));;
  
let rec ifPart a b =
  if (String.length a)=0 then true
  else if (String.length b)=0 then false
  else if (String.get a 0)=(String.get b 0) then ifPart (cutStr a) (cutStr b) 
  else ifPart (cutStr b);;
  
  ifPart "raz" "obrazy";;
  ifPart "obraz" "raz";;




    
(* TASK 3 *)

  let drop n list =
    let rec dropn n list reslist i =
      if list=[] then reslist
      else if i mod n=0 then dropn n (List.tl list) reslist (i+1)
      else  dropn n (List.tl list) (reslist @ [List.hd list]) (i+1)
    in dropn n list [] 1;;
    
  drop 3 ['a';'b';'c';'d';'e';'f';'g';'h';'i'];;
