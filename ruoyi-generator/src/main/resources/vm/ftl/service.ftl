
public interface ${Domain}Service extends IService<${Domain}>{

    void save(${Domain}SaveRequest request);

    PageResponse<${Domain}QueryResponse> queryList(${Domain}QueryRequest request);

    void delete(Long id);
}
