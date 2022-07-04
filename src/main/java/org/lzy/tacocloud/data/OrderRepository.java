package org.lzy.tacocloud.data;

import org.lzy.tacocloud.domain.TacoOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<TacoOrder, Long> {

}
