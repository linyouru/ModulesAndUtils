package lyr.mybatisplus.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @ClassName DynamicDataSource
 * @Description TODO
 * @Author LYR
 * @Date 2019/8/9 15:27
 * @Version 1.0
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    /*
	 * 根据Key返回targetDataSources
	 */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSource();
    }
}
