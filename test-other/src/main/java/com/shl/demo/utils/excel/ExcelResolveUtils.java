package com.shl.demo.utils.excel;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExcelResolveUtils {

    private final String XLSX = ".xlsx";
    private final String XLS = ".xls";

    public static void main(String[] args) {
        ExcelResolveUtils utils = new ExcelResolveUtils();
        File f1 = new File("C:\\Users\\Lenovo\\Desktop\\开放平台1001.xlsx");
        try {
            JSONArray response_body = utils.readXLSX(f1, "response_body");
            JSONArray request_body = utils.readXLSX(f1, "request_body");
            JSONArray response_param = utils.readXLSX(f1, "response_param");
            JSONArray request_param = utils.readXLSX(f1, "request_param");
            JSONArray error_desc = utils.readXLSXErrorExcel(f1, "error_desc");
            JSONArray url = utils.readUrlExcel(f1, "url");
            CommonInfo commonInfo = utils.readXLSXCommon(f1, "common");
            String sql = "INSERT INTO `api_interface` (\n" +
                    "\t`interface_code`,\n" +
                    "\t`url`,\n" +
                    "\t`request_header`,\n" +
                    "\t`request_body`,\n" +
                    "\t`request_param`,\n" +
                    "\t`response_body`,\n" +
                    "\t`response_param`,\n" +
                    "\t`request_ex`,\n" +
                    "\t`response_ex`,\n" +
                    "\t`error_desc`,\n" +
                    "\t`mark`,\n" +
                    "\t`del`,\n" +
                    "\t`service_name`,\n" +
                    "\t`content` \n" +
                    ")\n" +
                    "VALUES\n" +
                    "\t(\n" +
                    "'" + commonInfo.getInterface_code() + "'," +
                    "'" + url + "',\n" +
                    "'',\n" +
                    "'" + request_body + "',\n" +
                    "'" + request_param + "',\n" +
                    "'" + response_body + "',\n" +
                    "'" + response_param + "',\n" +
                    "'" + commonInfo.getRequest_ex() + "',\n" +
                    "'" + commonInfo.getResponse_ex() + "',\n" +
                    "'" + error_desc + "',\n" +
                    "'" + commonInfo.getMark() + "',\n" +
                    "'0',\n" +
                    "'" + commonInfo.getService_name() + "',\n" +
                    "'" + commonInfo.getContent() + "'\n" +
                    ");";
            System.out.println(sql);
            System.out.println("======================");
            System.out.println(sql.replaceAll("[\\t\\n]", "").trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    /**
//     * 获取Excel文件（.xls和.xlsx都支持）
//     */
//    public JSONArray readExcel(File file) throws Exception {
//        int res = checkFile(file);
//
//        if (res == 0) {
//            System.out.println("File not found");
//        } else if (res == 1) {
//            return readXLSX(file, "");
//        } else if (res == 2) {
//            return readXLS(file);
//        } else {
//            System.out.println("暂不支持该文件格式");
//        }
//        JSONArray array = new JSONArray();
//        return array;
//    }

//    /**
//     * 判断File文件的类型
//     *
//     * @param file 传入的文件
//     * @return 0-文件为空，1-XLSX文件，2-XLS文件，3-其他文件
//     */
//    public int checkFile(File file) {
//        if (file == null) {
//            return 0;
//        }
//        String flieName = file.getName();
//        if (flieName.endsWith(XLSX)) {
//            return 1;
//        }
//        if (flieName.endsWith(XLS)) {
//            return 2;
//        }
//        return 3;
//    }

    /**
     * 读取XLSX文件
     */
    public JSONArray readXLSX(File file, String name) throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        Workbook book = new XSSFWorkbook(file);
//        Sheet sheet = book.getSheetAt(0);
        Sheet sheet = book.getSheet(name);
        return readRequestOrResponseExcel(sheet, book);
    }

    /**
     * 错误码信息
     */
    public JSONArray readXLSXErrorExcel(File file, String name) throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        Workbook book = new XSSFWorkbook(file);
        Sheet sheet = book.getSheet(name);
        return readErrorExcel(sheet, book);
    }

    /**
     * url信息
     */
    public JSONArray readUrlExcel(File file, String name) throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        Workbook book = new XSSFWorkbook(file);
        Sheet sheet = book.getSheet(name);
        return readUrl(sheet, book);
    }

    /**
     * 通用信息
     */
    public CommonInfo readXLSXCommon(File file, String name) throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        Workbook book = new XSSFWorkbook(file);
        Sheet sheet = book.getSheet(name);
        return readCommon(sheet, book);
    }

    private CommonInfo readCommon(Sheet sheet, Workbook book) throws IOException {
        int rowStart = sheet.getFirstRowNum();    // 首行下标
        int rowEnd = sheet.getLastRowNum();    // 尾行下标
        // 如果首行与尾行相同，表明只有一行，直接返回空数组
        if (rowStart == rowEnd) {
            book.close();
            return new CommonInfo();
        }
        List<CommonInfo> fieldList = new ArrayList<>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            CommonInfo field = new CommonInfo();
            // interface_code
            Cell cell2 = row.getCell(0);
            cell2.setCellType(CellType.STRING);
            if (StringUtils.isNotEmpty(cell2.getStringCellValue())) {
                field.setInterface_code(cell2.getStringCellValue());
            }

            // request_ex
            Cell cell3 = row.getCell(1);
            if (null != cell3 && Cell.CELL_TYPE_BLANK != cell3.getCellType()) {
                cell3.setCellType(CellType.STRING);
                if (StringUtils.isNotEmpty(cell3.getStringCellValue())) {
                    field.setRequest_ex(cell3.getStringCellValue().replaceAll("\\s", ""));
                } else {
                    field.setRequest_ex("");
                }
            } else {
                field.setRequest_ex("");
            }

            // response_ex
            Cell cell6 = row.getCell(2);
            if (null != cell6 && Cell.CELL_TYPE_BLANK != cell6.getCellType()) {
                cell6.setCellType(CellType.STRING);
                if (StringUtils.isNotEmpty(cell6.getStringCellValue())) {
                    field.setResponse_ex(cell6.getStringCellValue().replaceAll("\\s", ""));
                } else {
                    field.setResponse_ex("");
                }
            } else {
                field.setResponse_ex("");
            }

            // mark列
            Cell cell7 = row.getCell(3);
            if (null != cell7 && Cell.CELL_TYPE_BLANK != cell7.getCellType()) {
                cell7.setCellType(CellType.STRING);
                if (StringUtils.isNotEmpty(cell7.getStringCellValue())) {
                    field.setMark(cell7.getStringCellValue());
                } else {
                    field.setMark("");
                }
            } else {
                field.setMark("");
            }

            // service_name
            Cell cell8 = row.getCell(4);
            if (null != cell8 && Cell.CELL_TYPE_BLANK != cell8.getCellType()) {
                cell8.setCellType(CellType.STRING);
                if (StringUtils.isNotEmpty(cell8.getStringCellValue())) {
                    field.setService_name(cell8.getStringCellValue());
                } else {
                    field.setService_name("");
                }
            } else {
                field.setService_name("");
            }

            // content
            Cell cell9 = row.getCell(5);
            if (null != cell9 && Cell.CELL_TYPE_BLANK != cell9.getCellType()) {
                cell9.setCellType(CellType.STRING);
                if (StringUtils.isNotEmpty(cell9.getStringCellValue())) {
                    field.setContent(cell9.getStringCellValue().trim());
                } else {
                    field.setContent("");
                }
            } else {
                field.setContent("");
            }
            fieldList.add(field);
        }
        book.close();

        return fieldList.get(0);
    }

//    /**
//     * 读取XLS文件
//     */
//    public JSONArray readXLS(File file) throws IOException {
//        POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(file));
//        Workbook book = new HSSFWorkbook(poifsFileSystem);
//        Sheet sheet = book.getSheetAt(0);
//        return readRequestOrResponseExcel(sheet, book);
//    }

    public JSONArray readRequestOrResponseExcel(Sheet sheet, Workbook book) throws IOException {
        int rowStart = sheet.getFirstRowNum();    // 首行下标
        int rowEnd = sheet.getLastRowNum();    // 尾行下标
        // 如果首行与尾行相同，表明只有一行，直接返回空数组
        if (rowStart == rowEnd) {
            book.close();
            return new JSONArray();
        }
        List<ReqOrResParamInfo> fieldList = new ArrayList<>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            ReqOrResParamInfo field = new ReqOrResParamInfo();
            // id列
            Cell cell = row.getCell(0);
            cell.setCellType(CellType.STRING);
            field.setId(cell.getStringCellValue());
            // name列
            Cell cell2 = row.getCell(1);
            cell2.setCellType(CellType.STRING);
            if (StringUtils.isNotEmpty(cell2.getStringCellValue())) {
                field.setName(cell2.getStringCellValue());
            }

            // type列
            Cell cell3 = row.getCell(2);
            if (null != cell3 && Cell.CELL_TYPE_BLANK != cell3.getCellType()) {
                cell3.setCellType(CellType.STRING);
                if (StringUtils.isNotEmpty(cell3.getStringCellValue())) {
                    field.setType(cell3.getStringCellValue());
                } else {
                    field.setType("");
                }
            } else {
                field.setType("");
            }

            // required_fields列
            Cell cell4 = row.getCell(3);
            if (null != cell4 && Cell.CELL_TYPE_BLANK != cell4.getCellType()) {
                cell4.setCellType(CellType.STRING);
                if (StringUtils.isNotEmpty(cell4.getStringCellValue())) {
                    field.setRequired_fields(cell4.getStringCellValue());
                } else {
                    field.setRequired_fields("");
                }
            } else {
                field.setRequired_fields("");
            }

            //length列
            Cell cell5 = row.getCell(4);
            if (null != cell5 && Cell.CELL_TYPE_BLANK != cell5.getCellType()) {
                cell5.setCellType(CellType.STRING);
                if (StringUtils.isNotEmpty(cell5.getStringCellValue())) {
                    field.setLength(cell5.getStringCellValue());
                } else {
                    field.setLength("");
                }
            } else {
                field.setLength("");
            }

            // desc列
            Cell cell6 = row.getCell(5);
            if (null != cell6 && Cell.CELL_TYPE_BLANK != cell6.getCellType()) {
                cell6.setCellType(CellType.STRING);
                if (StringUtils.isNotEmpty(cell6.getStringCellValue())) {
                    field.setDesc(cell6.getStringCellValue());
                } else {
                    field.setDesc("");
                }
            } else {
                field.setDesc("");
            }

            // mark列
            Cell cell7 = row.getCell(6);
            if (null != cell7 && Cell.CELL_TYPE_BLANK != cell7.getCellType()) {
                cell7.setCellType(CellType.STRING);
                if (StringUtils.isNotEmpty(cell7.getStringCellValue())) {
                    field.setMark(cell7.getStringCellValue());
                } else {
                    field.setMark("");
                }
            } else {
                field.setMark("");
            }

            //parentId列
            Cell cell8 = row.getCell(7);
            if (null != cell8 && Cell.CELL_TYPE_BLANK != cell8.getCellType()) {
                cell8.setCellType(CellType.STRING);
                if (StringUtils.isNotEmpty(cell8.getStringCellValue())) {
                    field.setParentId(cell8.getStringCellValue().trim());
                }
            }

            fieldList.add(field);
        }
        book.close();

        Map<String, List<ReqOrResParamInfo>> collect = fieldList.stream().collect(Collectors.groupingBy(ReqOrResParamInfo::getParentId));
        List<ReqOrResParamInfo> root = collect.get("0");
        JSONArray ja = new JSONArray();

        // 空指针检查
        if (root != null) {
            root.forEach(reqOrResParamInfo -> {
                JSONObject jsonObject = createFieldJson(reqOrResParamInfo);
                ja.add(jsonObject);
                processChildren(jsonObject, collect, reqOrResParamInfo.getId());
            });
        }

        return ja;
    }

    // 递归处理子节点
    private void processChildren(JSONObject parentJson, Map<String, List<ReqOrResParamInfo>> collect, String parentId) {
        List<ReqOrResParamInfo> children = collect.get(parentId);
        if (CollectionUtil.isNotEmpty(children)) {
            JSONArray jsonArray = new JSONArray();
            parentJson.put("subArray", jsonArray);
            children.forEach(child -> {
                JSONObject childJson = createFieldJson(child);
                jsonArray.add(childJson);
                processChildren(childJson, collect, child.getId());
            });
        }
    }

    private JSONObject createFieldJson(ReqOrResParamInfo reqOrResParamInfo) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", reqOrResParamInfo.getName());
        jsonObject.put("type", reqOrResParamInfo.getType());
        jsonObject.put("required_fields", reqOrResParamInfo.getRequired_fields());
        jsonObject.put("length", reqOrResParamInfo.getLength());
        jsonObject.put("desc", reqOrResParamInfo.getDesc());
        jsonObject.put("mark", reqOrResParamInfo.getMark());
        return jsonObject;
    }


    /**
     * 解析数据
     */
    public JSONArray readErrorExcel(Sheet sheet, Workbook book) throws IOException {
        int rowStart = sheet.getFirstRowNum();    // 首行下标
        int rowEnd = sheet.getLastRowNum();    // 尾行下标
        // 如果首行与尾行相同，表明只有一行，直接返回空数组
        if (rowStart == rowEnd) {
            book.close();
            return new JSONArray();
        }
        List<ErrorDescInfo> fieldList = new ArrayList<>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            ErrorDescInfo field = new ErrorDescInfo();
            // code
            Cell cell2 = row.getCell(0);
            cell2.setCellType(CellType.STRING);
            if (StringUtils.isNotEmpty(cell2.getStringCellValue())) {
                field.setCode(cell2.getStringCellValue());
            }

            // type列
            Cell cell3 = row.getCell(1);
            if (null != cell3 && Cell.CELL_TYPE_BLANK != cell3.getCellType()) {
                cell3.setCellType(CellType.STRING);
                if (StringUtils.isNotEmpty(cell3.getStringCellValue())) {
                    field.setType(cell3.getStringCellValue());
                } else {
                    field.setType("");
                }
            } else {
                field.setType("");
            }

            // desc列
            Cell cell6 = row.getCell(2);
            if (null != cell6 && Cell.CELL_TYPE_BLANK != cell6.getCellType()) {
                cell6.setCellType(CellType.STRING);
                if (StringUtils.isNotEmpty(cell6.getStringCellValue())) {
                    field.setDesc(cell6.getStringCellValue());
                } else {
                    field.setDesc("");
                }
            } else {
                field.setDesc("");
            }

            // mark列
            Cell cell7 = row.getCell(3);
            if (null != cell7 && Cell.CELL_TYPE_BLANK != cell7.getCellType()) {
                cell7.setCellType(CellType.STRING);
                if (StringUtils.isNotEmpty(cell7.getStringCellValue())) {
                    field.setMark(cell7.getStringCellValue());
                } else {
                    field.setMark("");
                }
            } else {
                field.setMark("");
            }
            fieldList.add(field);
        }
        book.close();

        JSONArray ja = new JSONArray();

        // 空指针检查
        if (CollectionUtil.isNotEmpty(fieldList)) {
            fieldList.forEach(fieldInfo -> {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("code", fieldInfo.getCode());
                jsonObject.put("type", fieldInfo.getType());
                jsonObject.put("desc", fieldInfo.getDesc());
                jsonObject.put("mark", fieldInfo.getMark());
                ja.add(jsonObject);
            });
        }
        return ja;
    }

    /**
     * 解析数据
     */
    public JSONArray readUrl(Sheet sheet, Workbook book) throws IOException {
        int rowStart = sheet.getFirstRowNum();    // 首行下标
        int rowEnd = sheet.getLastRowNum();    // 尾行下标
        // 如果首行与尾行相同，表明只有一行，直接返回空数组
        if (rowStart == rowEnd) {
            book.close();
            return new JSONArray();
        }
        List<UrlInfo> fieldList = new ArrayList<>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            UrlInfo field = new UrlInfo();
            // env_name
            Cell cell2 = row.getCell(0);
            cell2.setCellType(CellType.STRING);
            if (StringUtils.isNotEmpty(cell2.getStringCellValue())) {
                field.setEnv_name(cell2.getStringCellValue());
            }

            // url
            Cell cell3 = row.getCell(1);
            if (null != cell3 && Cell.CELL_TYPE_BLANK != cell3.getCellType()) {
                cell3.setCellType(CellType.STRING);
                if (StringUtils.isNotEmpty(cell3.getStringCellValue())) {
                    field.setUrl(cell3.getStringCellValue());
                } else {
                    field.setUrl("");
                }
            } else {
                field.setUrl("");
            }
            fieldList.add(field);
        }
        book.close();

        JSONArray ja = new JSONArray();

        // 空指针检查
        if (CollectionUtil.isNotEmpty(fieldList)) {
            fieldList.forEach(fieldInfo -> {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("env_name", fieldInfo.getEnv_name());
                jsonObject.put("url", fieldInfo.getUrl());
                ja.add(jsonObject);
            });
        }
        return ja;
    }


//    /**
//     * 获取每个单元格的数据
//     *
//     * @param cell   单元格对象
//     * @param rowNum 第几行
//     * @param index  该行第几个
//     * @param book   主要用于关闭流
//     * @param isKey  是否为键：true-是，false-不是。 如果解析Json键，值为空时报错；如果不是Json键，值为空不报错
//     */
//    public String getValue(Cell cell, int rowNum, int index, Workbook book, boolean isKey) throws IOException {
//
//        // 空白或空
//        if (cell == null || cell.getCellTypeEnum() == CellType.BLANK) {
//            if (isKey) {
//                book.close();
//                throw new NullPointerException(String.format("the key on row %s index %s is null ", ++rowNum, ++index));
//            } else {
//                return "";
//            }
//        }
//
//        // 1. String类型
//        if (cell.getCellTypeEnum() == CellType.STRING) {
//            String val = cell.getStringCellValue();
//            if (val == null || val.trim().length() == 0) {
//                if (book != null) {
//                    book.close();
//                }
//                return "";
//            }
//            return val.trim();
//        }
//
//        // 2. 公式 CELL_TYPE_FORMULA
//        if (cell.getCellTypeEnum() == CellType.FORMULA) {
//            return cell.getStringCellValue();
//        }
//
//        // 4. 布尔值 CELL_TYPE_BOOLEAN
//        if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
//            return cell.getBooleanCellValue() + "";
//        }
//
//        // 5.	错误 CELL_TYPE_ERROR
//        return "";
//    }

}
