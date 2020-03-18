package com.cn.poi.Utils;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.apache.commons.lang.ArrayUtils;

import java.io.InputStream;
import java.util.List;

public class ImportExcel {

    /**
     * 读取excel表格内容返回List<Bean>
     * @param inputStream excel文件流
     * @param head        表头数组
     * @param headerAlias 表头别名数组
     * @return
     */
    public static <T> List<T> importExcelList(InputStream inputStream, String[] head, String[] headerAlias, Class<T> bean) {
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Object> header = reader.readRow(1);
        //替换表头关键字
        if (ArrayUtils.isEmpty(head) || ArrayUtils.isEmpty(headerAlias) || head.length != headerAlias.length) {
            return null;
        } else {
            for (int i = 0; i < head.length; i++) {
                if (head[i].equals(header.get(i))) {
                    reader.addHeaderAlias(head[i], headerAlias[i]);
                } else {
                    return null;
                }
            }
        }
        //读取指点行开始的表数据（从0开始）
        List<T> read = reader.read(1, 2, bean);
        return read;
    }


}
