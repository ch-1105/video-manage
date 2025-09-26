
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/${do_main}")
public class ${Domain}Controller {

    @Resource
    private ${Domain}Service ${domain}Service;

    @PostMapping("/save")
    public Result<Object> save(@Valid @RequestBody ${Domain}SaveRequest request) {
        ${domain}Service.save(request);
        return Result.success();
    }

    @GetMapping("/query-list")
    public Result<PageResponse<${Domain}QueryResponse>> queryList(@Valid ${Domain}QueryRequest request) {
        PageResponse<${Domain}QueryResponse> list = ${domain}Service.queryList(request);
        return Result.success(list);
    }

    @DeleteMapping("/delete/{id}")
    public Result<Object> delete(@PathVariable Long id) {
        ${domain}Service.delete(id);
        return Result.success();
    }

    @DeleteMapping("/batchDelete")
    public Result<Object> batchDelete(@RequestBody List<Long> ids) {
        ${domain}Service.removeByIds(ids);
        return Result.success();
    }

}
