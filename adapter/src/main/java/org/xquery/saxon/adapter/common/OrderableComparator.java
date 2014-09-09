package org.xquery.saxon.adapter.common;

import java.util.Comparator;

public class OrderableComparator implements Comparator<Orderable> {

    public static final OrderableComparator ORDERABLE_COMPARATOR = new OrderableComparator();

    private OrderableComparator() {
    }

    @Override
    public int compare(Orderable o1, Orderable o2) {
        return Integer.compare(o1.getOrder(), o2.getOrder());
    }
}
