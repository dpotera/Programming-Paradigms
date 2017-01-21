
(* TASK 2 *)

let rec fib n =
  if n=0 || n=1 then n
  else fib(n-2) + fib(n-1);;

let fibRec n =
  let rec fib(n,a,b) = if n=0 then a else fib(n-1,b,a+b)
  in fib(n,0,1);;
  
  fib 42;;
  fibRec 42;;

(* TASK 3 *)
  let e = 0.000000000000001;;  
  let root3 n =
    let rec root3_tail(n,x) =
      if(abs_float(x*.x*.x-.n) <= e*.abs_float(n)) then x
      else root3_tail(n,x+.(n/.(x*.x)-.x)/.3.)
    in root3_tail(n,if n>1. then n/.3. else n);;

   root3 2.0;; 

    
(* TASK 4 a) *)    
  let a = [-2;-1;0;1;2];;
  let [_;_;x;_;_] = a;;
(*        b) *)     
  let b = [(1,2);(0,1)];;
  let [_;(x,_)] = b;;
    
    
(* TASK 5 *)

  let rec initSegment a b =
    if a=[] then true
    else if List.hd a <> List.hd b then false
    else initSegment (List.tl a) (List.tl b);;

  initSegment [1;2;3] [1;2;3;4];;
  initSegment [] [1;2;3];;
  initSegment [1;2;3] [1;2;3];;
  initSegment [1;2;2] [1;2;3];;
  initSegment [] [];;

(* TASK 6 *)

  let replaceNth(list,n,r) =
    let rec repl(list,n,r,i) = match list with
      | [] -> list
      | f :: rest -> if i=n then r::rest else f::repl(rest,n,r,i+1)
    in repl(list,n,r,0);;
    
    replaceNth([1;2;3;4],2,100);;
    
