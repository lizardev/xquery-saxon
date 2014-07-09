declare variable $flag as xs:boolean := fn:true();
let $x := 17
return if ($flag) then
    "a"
else
    "b"