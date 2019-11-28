package com.wang.novelweb.Controller;

import com.wang.novelweb.util.ExcelManager;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@Log4j
@RequestMapping("/duty")

public class DutyController {

    //public static void generateExcel(HttpServletResponse response, String fileName, String sheetName, String title,
    //                                     String[] headers, int[] headersLen, List<List<Object>> listCon) {


    List<String> personnel;

    public void exportCompareByAppFoodCode(Date startDate, Date endDate, HttpServletResponse response) {
        //List<String> ren = new ArrayList<>();
        String[] ren = {"","","","","","","","","","","","","","","","","","","","","","",};

        // 封装导出
        Map map = getPerDaysByStartAndEndDate(startDate.toString(),endDate.toString());
        ExcelManager.generateExcel(null,null,null,null,null,null,null);
    }
    private static Map<String,Object> getPerDaysByStartAndEndDate(String startDate, String endDate) {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            Date sDate = format.parse(startDate);
            Date eDate = format.parse(endDate);
            long start = sDate.getTime();
            long end = eDate.getTime();
            if (start > end) {
                return null;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(eDate);
            List<Date> res = new ArrayList<Date>();
            while (end >= start) {
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                end = calendar.getTimeInMillis();
            }
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
