package me.chnu.apiwrapper.presentation.api.plan;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import me.chnu.apiwrapper.application.plan.PlanReadService;
import me.chnu.apiwrapper.application.plan.PlanWriteService;
import me.chnu.apiwrapper.presentation.api.ApiResponse;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plans")
@RequiredArgsConstructor
public class PlanController {
    private final PlanWriteService planWriteService;
    private final PlanReadService planReadService;

    @PostMapping
    public ResponseEntity<ApiResponse<String>> create(@RequestBody PlanDTOs.New newPlan) {
        planWriteService.create(newPlan.toPlan());

        return ResponseEntity.accepted().body(ApiResponse.ok("Successfully created"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageImpl<PlanDTOs.Info>>> getAll(
            @ParameterObject
            @PageableDefault Pageable pageable
    ) {
        List<PlanDTOs.Info> infoList = planReadService.getAll(pageable).stream()
                .map(PlanDTOs.Info::from)
                .toList();
        long count = planReadService.count();

        PageImpl<PlanDTOs.Info> page = new PageImpl<>(infoList, pageable, count);

        return ResponseEntity.ok(ApiResponse.ok(page));
    }

    @GetMapping("/{plan-id}")
    public ResponseEntity<ApiResponse<PlanDTOs.DetailInfo>> get(@PathVariable("plan-id") Long planId) {
        PlanDTOs.DetailInfo planDetailInfo = planReadService.get(planId)
                .as(PlanDTOs.DetailInfo::from);

        return ResponseEntity.ok(ApiResponse.ok(planDetailInfo));
    }

    @PutMapping("/{plan-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ApiResponse<String>> update(@PathVariable("plan-id") Long planId, @RequestBody PlanDTOs.New newPlan) {
        planWriteService.update(planId, newPlan.name(), newPlan.description());

        return ResponseEntity.accepted().body(ApiResponse.ok("Successfully updated"));
    }

    @DeleteMapping("/{plan-id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable("plan-id") Long planId) {
        planWriteService.delete(planId);

        return ResponseEntity.accepted().body(ApiResponse.ok("Successfully deleted"));
    }
}
