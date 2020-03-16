package lyr.utils.poi.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Java8 localDate 自定义转换器
 *
 * @author xula
 */
public class LocalDateStringConvert implements Converter<LocalDate> {

    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public Class supportJavaTypeKey() {
        return LocalDate.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public LocalDate convertToJavaData(CellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if (contentProperty != null || contentProperty.getDateTimeFormatProperty() != null) {
            formatter = DateTimeFormatter.ofPattern(contentProperty.getDateTimeFormatProperty().getFormat());
        }
        return LocalDate.parse(cellData.getStringValue(), formatter);
    }

    @Override
    public CellData convertToExcelData(LocalDate value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        if (contentProperty != null && contentProperty.getDateTimeFormatProperty() != null) {
            String format = contentProperty.getDateTimeFormatProperty().getFormat();
            formatter = DateTimeFormatter.ofPattern(format);
        }
        return new CellData(formatter.format(value));
    }
}
