package me.chnu.apiwrapper.application.plan;

import lombok.RequiredArgsConstructor;
import me.chnu.apiwrapper.domain.plan.Plan;
import me.chnu.apiwrapper.domain.plan.PlanRepository;
import me.chnu.apiwrapper.util.annotation.WriteService;

@WriteService
@RequiredArgsConstructor
public class PlanWriteService {
    private final PlanRepository planRepository;

    public void create(Plan plan) {
        planRepository.save(plan);
    }

    public void delete(Long id) {
        planRepository.deleteById(id);
    }

    public void update(Long id, String name, String description) {
        planRepository.findById(id)
                .ifPresentOrElse(
                    foundPlan -> foundPlan.update(name, description),
                    () -> {
                        throw new IllegalArgumentException("Plan not found");
                    }
                );
    }
}
