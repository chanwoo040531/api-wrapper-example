package me.chnu.apiwrapper.application.plan;

import lombok.RequiredArgsConstructor;
import me.chnu.apiwrapper.domain.plan.Plan;
import me.chnu.apiwrapper.domain.plan.PlanRepository;
import me.chnu.apiwrapper.util.annotation.ReadService;
import me.chnu.apiwrapper.util.exception.PlanNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

@ReadService
@RequiredArgsConstructor
public class PlanReadService {
    private final PlanRepository planRepository;

    public Plan get(Long id) {
        return planRepository.findById(id)
                .orElseThrow(() -> new PlanNotFoundException("Plan not found"));
    }

    public List<Plan> getAll(Pageable pageable) {
        return planRepository.findAllByPage(pageable);
    }

    public long count() {
        return planRepository.count();
    }
}
