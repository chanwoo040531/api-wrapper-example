package me.chnu.apiwrapper.presentation.api.plan;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import me.chnu.apiwrapper.application.plan.PlanReadService;
import me.chnu.apiwrapper.application.plan.PlanWriteService;
import me.chnu.apiwrapper.presentation.api.ApiResponse;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plans")
@RequiredArgsConstructor
public class PlanController {
    private final PlanWriteService planWriteService;
    private final PlanReadService planReadService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody PlanDTOs.New newPlan) {
        planWriteService.create(newPlan.toPlan());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<PageImpl<PlanDTOs.Info>> getAll(
            @Parameter(hidden = true)
            @PageableDefault Pageable pageable
    ) {
        List<PlanDTOs.Info> infoList = planReadService.getAll(pageable).stream()
                .map(PlanDTOs.Info::from)
                .toList();
        long count = planReadService.count();

        PageImpl<PlanDTOs.Info> page = new PageImpl<>(infoList, pageable, count);

        return ApiResponse.ok(page);
    }

    @GetMapping("/{plan-id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<PlanDTOs.DetailInfo> get(@PathVariable("plan-id") Long planId) {
        PlanDTOs.DetailInfo planDetailInfo = planReadService.get(planId)
                .as(PlanDTOs.DetailInfo::from);

        return ApiResponse.ok(planDetailInfo);
    }

    @PutMapping("/{plan-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("plan-id") Long planId, @RequestBody PlanDTOs.New newPlan) {
        planWriteService.update(planId, newPlan.name(), newPlan.description());
    }

    @DeleteMapping("/{plan-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("plan-id") Long planId) {
        planWriteService.delete(planId);
    }
}
