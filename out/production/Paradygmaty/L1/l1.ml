
  (* TASK 1 *)

  let penultimate lst =
    if List.length lst<2 then raise(Failure "list too short")
    else List.hd(List.tl(List.rev lst));;

  penultimate [1;2;3;4;5];;
  penultimate ['X'];;
  penultimate ['a';'b';'c';'d';'e'];;

  (* TASK 3 *)
    
  let isPrime x =
    if x<2 then false
    else let rec checkDiv n =
	   n*n>x || (x mod n <> 0 && checkDiv(n+1)) in checkDiv 2;;

  let rec primesRange x y =
    if x>y || x<0 || y<0 then []
    else if isPrime x then [x]::primesRange(x+1)y else primesRange(x+1)y;;

    primesRange 2 23;;
 
