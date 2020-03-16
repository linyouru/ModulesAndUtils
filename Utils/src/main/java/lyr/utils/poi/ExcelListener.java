package lyr.utils.poi;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ExcelListener
 * @Description TODO
 * @Author LYR
 * @Date 2020/3/11 17:31
 * @Version 1.0
 **/
public class ExcelListener extends AnalysisEventListener {

    private static final Logger log = LoggerFactory.getLogger(ExcelListener.class);

    //自定义用于暂时存储data
    private List datas = new ArrayList();

    private static final int BATCH_COUNT = 1000;

    private Integer batchCount = BATCH_COUNT;

    /**
     * 是否需要分批执行
     */
    private boolean isCallBack = false;

    /**
     * 回调方法
     */
    private ICallback iCallback;


    /**
     * 这个每一条数据解析都会来调用
     * @param data
     * @param context
     * @Author LinYouRu
     * @Date 10:38 2020/3/12
     * @return void
     **/
    @Override
    public void invoke(Object data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSONUtil.toJsonStr(data));
        datas.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (isCallBack && datas.size() >= batchCount) {
            saveData();
            // 存储完成清理 list
            datas.clear();
        }
    }

    /**
     * 读取完之后的操作
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();
        log.info("所有数据解析完成！");
    }

    private void saveData() {
        if (isCallBack) {
            iCallback.todo(datas);
        }
    }

    public List getDatas() {
        return datas;
    }

    public void setBatchCount(Integer batchCount) {
        this.batchCount = batchCount;
    }

    public void setiCallback(ICallback iCallback) {
        this.iCallback = iCallback;
        this.isCallBack = true;
    }
}
