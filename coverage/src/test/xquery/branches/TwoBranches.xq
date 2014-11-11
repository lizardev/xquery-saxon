declare variable $switch as xs:boolean external;

if ($switch) then
    fn:true()
else
    fn:false()