declare variable $flag as xs:boolean := fn:true();

if ($flag) then
    "a"
else
    "b"