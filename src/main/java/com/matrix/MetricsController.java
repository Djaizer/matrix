package com.matrix;

import com.matrix.utils.MatrixAdapter;
import com.matrix.utils.ServiceDataCollector;
import com.matrix.view.Arrow;
import com.matrix.view.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;

@Controller
public class MetricsController {

    @Autowired
    ServiceDataCollector serviceDataCollector;

    @RequestMapping("/services-states")
    public ModelAndView servicesStates(@RequestParam(name = "N", required = false, defaultValue = "7") Integer matrixSize, Map<String, Object> model) {
        int[][] arrows = serviceDataCollector.collectServicesMatrix(matrixSize);
        Map<String, String> services = serviceDataCollector.collectServicesStates(matrixSize);
        List<Arrow> arrowsList = MatrixAdapter.convertMatrixToArrows(arrows);
        List<Service> servicesList = MatrixAdapter.applyServices(services);

        model.put("matrixSize", matrixSize);
        model.put("arrowList", arrowsList);
        model.put("servicesList", servicesList);
        return new ModelAndView("/services-states");
    }




}
