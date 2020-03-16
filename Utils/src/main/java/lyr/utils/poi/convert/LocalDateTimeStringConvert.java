package lyr.utils.poi.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Java8 LocalDateTim 自定义转换器
 * @author xula
 */
public class LocalDateTimeStringConvert implements Converter<LocalDateTime> {


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Class supportJavaTypeKey() {
        return LocalDateTime.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public LocalDateTime convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if (contentProperty != null || contentProperty.getDateTimeFormatProperty() != null) {
            formatter = DateTimeFormatter.ofPattern(contentProperty.getDateTimeFormatProperty().getFormat());
        }
        return LocalDateTime.parse(cellData.getStringValue(), formatter);
    }

    @Override
    public CellData convertToExcelData(LocalDateTime value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if (contentProperty != null && contentProperty.getDateTimeFormatProperty() != null) {
            String format = contentProperty.getDateTimeFormatProperty().getFormat();
            formatter = DateTimeFormatter.ofPattern(format);
        }
        return new CellData(formatter.format(value));
    }
}
