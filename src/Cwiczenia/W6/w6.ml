let swap tab i j =
	let aux = tab.(i) in tab.(i) <- tab.(j); tab.(j) <- aux;;

let choose_pivot tab m n = tab.((m+n)/2);;

let partition tab l r =
  let i=ref l and j=ref r and pivot=choose_pivot tab l r
  in while !i <= !j do
    while tab.(!i) < pivot do incr i done;
    while pivot < tab.(!j) do decr j done;
    if !i <= !j
    then begin swap tab !i !j; incr i; decr j end
  done;
  (!i,!j)
  ;;

let rec quick tab l r =
  if l < r then
    let (i,j) = partition tab l r
    in if j-l < r-i (* usprawnienie 3 *)
      then (quick tab l j; quick tab i r)
      else (quick tab i r; quick tab l j)
  else ();;

let quicksort tab = quick tab 0 ((Array.length tab)-1);;

let t1 = [|4;8;1;12;7;3;1;9|];;

quicksort t1;;

t1;;

let test msg = print_string msg;;

let f1 bool = if bool then
	let _ = test "1" in test "2" else ();; 

let f2 bool = if bool then
	(test "1"; test "2") else ();; 

f1 true;;
f2 true;;