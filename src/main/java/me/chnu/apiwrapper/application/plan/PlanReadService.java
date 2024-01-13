package me.chnu.apiwrapper.application.plan;

import lombok.RequiredArgsConstructor;
import me.chnu.apiwrapper.domain.plan.Plan;
import me.chnu.apiwrapper.domain.plan.PlanRepository;
import me.chnu.apiwrapper.util.annotation.ReadService;
import me.chnu.apiwrapper.util.exception.PlanNotFoundException;

import java.util.List;

@ReadService
@RequiredArgsConstructor
public class PlanReadService {
    private final PlanRepository planRepository;

    public Plan get(Long id) {
        return planRepository.findById(id)
                .orElseThrow(() -> new PlanNotFoundException("Plan not found"));
    }

    public List<Plan> getAll() {
        return planRepository.findAll();
    }
}
