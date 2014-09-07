declare namespace tour = "http://xquery.coverage/functions";

declare default function namespace "http://xquery.coverage/functions";

declare function contentProvider($content) {
    if (fn:exists($content)) then
        <content>{$content}</content>
    else
        ()
};


declare function composite($first, $second) {
    if ($first and $second) then
        <first><second>{contentProvider(())}</second></first>
    else if ($first) then
        contentProvider(<first/>)
    else
        ()
};



composite(fn:true(), fn:false()),
composite(fn:true(), fn:true()),
composite(fn:false(), fn:false())

