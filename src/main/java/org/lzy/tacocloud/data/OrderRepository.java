package org.lzy.tacocloud.data;

import org.lzy.tacocloud.domain.TacoOrder;

public interface OrderRepository {

    TacoOrder save(TacoOrder order);
}
