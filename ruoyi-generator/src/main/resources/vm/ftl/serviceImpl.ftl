
import java.util.List;

@Service
public class ${Domain}ServiceImpl extends ServiceImpl<${Domain}Mapper, ${Domain}> implements ${Domain}Service {

    private static final Logger log = LoggerFactory.getLogger(${Domain}Service.class);

    @Resource
    private ${Domain}Mapper ${domain}Mapper;

    @Override
    public void save(${Domain}SaveRequest request) {
        DateTime now = DateTime.now();
        ${Domain} ${domain} = BeanUtil.copyProperties(request, ${Domain}.class);
        if (ObjectUtil.isNull(${domain}.getId())) {
            ${domain}.setId(IdUtil.getSnowflakeNextId());
            ${domain}.setCreateTime(now);
            ${domain}.setUpdateTime(now);
            ${domain}Mapper.insert(${domain});
        } else {
            ${domain}.setUpdateTime(now);
            ${domain}Mapper.updateById(${domain});
        }
    }
    @Override
    public PageResponse<${Domain}QueryResponse> queryList(${Domain}QueryRequest request) {
        QueryWrapper<${Domain}> ${domain}Wrapper = new QueryWrapper<>();
        ${domain}Wrapper.orderByDesc("id");

        log.info("查询页码：{}", request.getPageNum());
        log.info("每页条数：{}", request.getPageSize());
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<${Domain}> ${domain}List = ${domain}Mapper.selectList(${domain}Wrapper);

        PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}List);
        log.info("总行数：{}", pageInfo.getTotal());
        log.info("总页数：{}", pageInfo.getPages());

        List<${Domain}QueryResponse> list = BeanUtil.copyToList(${domain}List, ${Domain}QueryResponse.class);

        PageResponse<${Domain}QueryResponse> pageResponse = new PageResponse<>();
        pageResponse.setTotal(pageInfo.getTotal());
        pageResponse.setList(list);
        return pageResponse;
    }
    @Override
    public void delete(Long id) {
        ${domain}Mapper.deleteById(id);
    }
}
