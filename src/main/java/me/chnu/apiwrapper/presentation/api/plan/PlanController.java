package me.chnu.apiwrapper.presentation.api.plan;

import lombok.RequiredArgsConstructor;
import me.chnu.apiwrapper.application.plan.PlanReadService;
import me.chnu.apiwrapper.application.plan.PlanWriteService;
import me.chnu.apiwrapper.presentation.api.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("ClassEscapesDefinedScope")
@RestController
@RequestMapping("/api/v1/plans")
@RequiredArgsConstructor
public class PlanController {
    private final PlanWriteService planWriteService;
    private final PlanReadService planReadService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody NewPlan newPlan) {
        planWriteService.create(newPlan.toPlan());

    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<PlanInfo>> getAll() {
        List<PlanInfo> planInfoList = planReadService.getAll().stream()
                .map(PlanInfo::from)
                .toList();
        return ApiResponse.ok(planInfoList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<PlanDetailInfo> get(@PathVariable Long id) {
        PlanDetailInfo planDetailInfo = planReadService.get(id)
                .as(PlanDetailInfo::from);

        return ApiResponse.ok(planDetailInfo);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody NewPlan newPlan) {
        planWriteService.update(id, newPlan.name(), newPlan.description());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        planWriteService.delete(id);
    }
}
