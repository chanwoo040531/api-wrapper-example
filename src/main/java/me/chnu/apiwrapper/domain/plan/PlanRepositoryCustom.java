package me.chnu.apiwrapper.domain.plan;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlanRepositoryCustom {

    List<Plan> findAllByPage(Pageable pageable);
}
