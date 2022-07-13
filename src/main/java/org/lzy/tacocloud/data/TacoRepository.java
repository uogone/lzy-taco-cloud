package org.lzy.tacocloud.data;

import org.lzy.tacocloud.domain.Taco;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TacoRepository extends CrudRepository<Taco, String> {

    List<Taco> findAll(Pageable page);
}
