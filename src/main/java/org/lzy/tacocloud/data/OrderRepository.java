package org.lzy.tacocloud.data;

import org.lzy.tacocloud.domain.TacoOrder;

public interface OrderRepository {

    void save(TacoOrder order);
}
