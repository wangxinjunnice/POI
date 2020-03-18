package com.cn.poi.Poi;

import com.cn.poi.Utils.DownloadUtils;
import com.cn.poi.Utils.ImportExcel;
import com.cn.poi.Utils.PublicConstant;
import com.cn.poi.entity.User;
import com.cn.poi.service.UserService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RestController
@RequestMapping("/downFile")
public class DownLiadExcel {

    @Autowired
    private UserService userService;

    /**
     * 导出Excel
     * @param response
     * @throws Exception
     */
    @GetMapping("/excels")
    public void downFiles(HttpServletResponse response) throws Exception {

        //获取导出Excel所有的数据
        List<User> list = userService.listAll();

        //加载模板
        Resource resource=new ClassPathResource("templates/用户信息.xlsx");
        FileInputStream fileInputStream=new FileInputStream(resource.getFile());

        //根据模板创建工作簿
        Workbook wb=new XSSFWorkbook(fileInputStream);

        //读取工作表
        Sheet sheet = wb.getSheetAt(0);

        //抽取公共样式
        Row row2 = sheet.getRow(2);
        CellStyle styles []=new CellStyle[row2.getLastCellNum()];
        for (int i = 0; i < row2.getLastCellNum(); i++) {
            Cell cell = row2.getCell(i);
            styles[i] = cell.getCellStyle();
        }

        //构造单元格
        int rowIndex=2;
        //创建并且往行中添加数据
        Cell cell =null;
        for (User user : list) {
            Row row1 = sheet.createRow(rowIndex++);

            //编号
            cell = row1.createCell(0);
            cell.setCellValue(user.getId());
            cell.setCellStyle(styles[0]);

            //用户名称
            cell = row1.createCell(1);
            cell.setCellValue(user.getUsername());
            cell.setCellStyle(styles[1]);

            //生日
            cell = row1.createCell(2);
            cell.setCellValue(user.getBirthday());
            cell.setCellStyle(styles[2]);

            //性别
            cell = row1.createCell(3);
            cell.setCellValue(user.getSex());
            cell.setCellStyle(styles[3]);

            //地址
            cell = row1.createCell(4);
            cell.setCellValue(user.getAddress());
            cell.setCellStyle(styles[4]);


        }

        //完成下载
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        wb.write(os);
        new DownloadUtils().download(os, response,"用户信息.xlsx");

    }


    /**
     * 批量导入数据，有则更新，无则新增
     * @param file
     * @return
     */
    @PostMapping("/importUser")
    public Object importList(@RequestParam(value = "file", required = false) MultipartFile file) {

        try {
            List<User> users = ImportExcel.importExcelList(file.getInputStream(), PublicConstant.excelHead, PublicConstant.excelHeadAlias, User.class);
            userService.insertByBatch(users);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }



    /**
     * 下载模板
     */
    @GetMapping("/download")
    public Object templateDownload(HttpServletResponse response){

        try {
            InputStream file = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.xlsx");

            byte[] buffer=new byte[file.available()];
            file.read(buffer);
            file.close();
            response.setHeader("Content-Disposition","attachment;filename=test.xlsx");
            response.setContentType("application/binary;charset=UTF-8");
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(buffer);
            outputStream.flush();
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
