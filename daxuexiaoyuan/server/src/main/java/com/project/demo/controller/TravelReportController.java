package com.project.demo.controller;

import com.project.demo.entity.TravelReport;
import com.project.demo.service.TravelReportService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *出行报备：(TravelReport)表控制层
 *
 */
@RestController
@RequestMapping("/travel_report")
public class TravelReportController extends BaseController<TravelReport,TravelReportService> {

    /**
     *出行报备对象
     */
    @Autowired
    public TravelReportController(TravelReportService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
