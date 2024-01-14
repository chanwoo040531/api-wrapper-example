package me.chnu.apiwrapper.presentation.api.plan;

import com.fasterxml.jackson.annotation.JsonFormat;
import me.chnu.apiwrapper.domain.plan.Plan;

import java.time.LocalDateTime;

record NewPlan(String name, String description) {

    public Plan toPlan() {
        return Plan.builder()
                .name(name)
                .description(description)
                .build();
    }
}

record PlanInfo(Long id, String name) {
   public static PlanInfo from(Plan plan) {
        return new PlanInfo(plan.getId(), plan.getName());
   }
}

record PlanDetailInfo(
        Long id,
        String name,
        String description,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime lastUpdatedAt
) {
    public static PlanDetailInfo from(Plan plan) {
        return new PlanDetailInfo(
                plan.getId(),
                plan.getName(),
                plan.getDescription(),
                plan.getCreatedAt(),
                plan.getLastUpdatedAt()
        );
    }

}