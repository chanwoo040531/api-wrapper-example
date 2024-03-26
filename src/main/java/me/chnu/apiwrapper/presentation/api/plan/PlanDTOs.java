package me.chnu.apiwrapper.presentation.api.plan;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import me.chnu.apiwrapper.domain.plan.Plan;

import java.lang.annotation.*;
import java.time.LocalDateTime;

public final class PlanDTOs {
    public record New(String name, String description) {

        public Plan toPlan() {
            return Plan.builder()
                    .name(name)
                    .description(description)
                    .build();
        }
    }

    public record Info(

            @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
            Long planId,

            @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
            String name
    ) {
        public static Info from(Plan plan) {
            return new Info(plan.getId(), plan.getName());
        }
    }

    public record DetailInfo(
            @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
            Long id,
            @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
            String name,
            @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
            String description,
            @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
            LocalDateTime createdAt,
            @Schema(requiredMode = Schema.RequiredMode.REQUIRED)
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
            LocalDateTime lastUpdatedAt
    ) {
        public static DetailInfo from(Plan plan) {
            return new DetailInfo(
                    plan.getId(),
                    plan.getName(),
                    plan.getDescription(),
                    plan.getCreatedAt(),
                    plan.getLastUpdatedAt()
            );
        }
    }
}
