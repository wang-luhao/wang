package com.wang.novelweb.Controller;

import com.wang.novelweb.Service.PersonnelService;
import com.wang.novelweb.util.ExcelManager;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@Log4j
@RequestMapping("/duty")

public class DutyController {

    private PersonnelService personnelService;

    @Autowired
    public void setPersonnelService(PersonnelService personnelService) {
        this.personnelService = personnelService;
    }

    @ResponseBody
    @RequestMapping("exportDuty")
    public Map exportDuty(String startDate, String endDate) {
        List<String> names = personnelService.selectName();
        List<String> positions = new ArrayList<>();
        List<Map> maps = new ArrayList<>();
        positions.add("郭总办公室");
        positions.add("部门");
        positions.add("东休闲区");
        List<String> groups = new ArrayList<>();
        groups.add("二组");
        groups.add("三组");
        groups.add("一组");
        Calendar calendar = Calendar.getInstance();
        Map<String,Object> resultMap = new HashMap<>();
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        long nd = 1000 * 24 * 60 * 60;
        try {
            Date sDate = format.parse(startDate);
            Date eDate = format.parse(endDate);
            List<String> dates = getPerDaysByStartAndEndDate(sDate,eDate,format);
            if (dates != null) {
                int day = dates.size();
                int start = 0;
                int days = (int)((format.parse(dates.get(0)).getTime()-format.parse("20191202").getTime())/nd);
                int j=3;
                for(int i = 0; i<days; i++){
                    calendar.setTime(format.parse("20191202"));
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
                        String string = groups.get(0);
                        groups.remove(0);
                        groups.add(string);
                        j = j - 3;
                        continue;
                    }
                    start = (3*i+j)%names.size();
                    if(i>0&&start == 0){
                        positions.add(positions.get(0));
                        positions.remove(0);
                    }
                }
                for(int i = 0; i<day; i++){
                    Map<String,Object> map = new HashMap<>();
                    map.put("日期",dates.get(i));
                    calendar.setTime(format.parse(dates.get(i)));
                    if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
                        map.put("周","周六");
                        map.put("小组",groups.get(0));
                        map.put("位置",positions);
                        maps.add(map);
                        groups.add(positions.get(0));
                        groups.remove(0);
                        start = start - 3;
                        continue;
                    }
                    switch (calendar.get(Calendar.DAY_OF_WEEK)){
                        case Calendar.MONDAY:
                            map.put("周","周一");
                            break;
                        case Calendar.TUESDAY:
                            map.put("周","周二");
                            break;
                        case Calendar.WEDNESDAY:
                            map.put("周","周三");
                            break;
                        case Calendar.THURSDAY:
                            map.put("周","周四");
                            break;
                        case Calendar.FRIDAY:
                            map.put("周","周五");
                            break;
                    }
                    int start1 = (3*i+start)%names.size();
                    int end1 = (3*i+start+3)%names.size();
                    if(i>0&&start1==0){
                        String string = positions.get(0);
                        positions.remove(0);
                        positions.add(string);
                    }
                    if(end1<start1){
                        List<String> list = new ArrayList<>(names.subList(start1, names.size()));
                        List<String> positionsCopy = new ArrayList<>(positions.subList(0,3));
                        list.addAll(names.subList(0,end1));
                        map.put("人员",list);
                        map.put("位置",positionsCopy);
                    }
                    else{
                        List<String> positionsCopy = new ArrayList<>(positions.subList(0,3));
                        map.put("人员",names.subList(start1,end1));
                        map.put("位置",positionsCopy);
                    }
                    maps.add(map);
                }
            }
            resultMap.put("值日表",maps);
            return resultMap;
        }catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
    private static List<String> getPerDaysByStartAndEndDate(Date startDate, Date endDate,DateFormat format) {
        long start = startDate.getTime();
        long end = endDate.getTime();
        if (start > end) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        List<String> res = new ArrayList<String>();
        while (end >= start) {
            if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                continue;
            }
            res.add(format.format(calendar.getTime()));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            start = calendar.getTimeInMillis();
        }
        return res;
    }
}
