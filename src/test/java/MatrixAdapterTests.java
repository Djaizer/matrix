import com.matrix.utils.MatrixAdapter;
import com.matrix.utils.ServiceDataCollector;
import com.matrix.view.Arrow;
import com.matrix.view.Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatrixAdapterTests {

    @Autowired
    ServiceDataCollector serviceDataCollector;


    @Test
    public void applyServicesTest() {
        Service expectedService1 = Service.builder().id(1).name("Service 1").serviceState("green").build();
        Service expectedService2 = Service.builder().id(2).name("Service 2").serviceState("red").build();
        Service expectedService3 = Service.builder().id(2).name("Service 3").serviceState("green").build();

        Map<String, String> services = new HashMap<>();
        services.put("Service 1", "RUNNING");
        services.put("Service 2", "STOPPED");
        List<Service> result = MatrixAdapter.applyServices(services);
        Assert.isTrue(result.size() == 2, "Actual services list size should be equals 2");
        Assert.isTrue(result.contains(expectedService1), "Actual services list should contain " + expectedService1.toString());
        Assert.isTrue(result.contains(expectedService2), "Actual services list should contain " + expectedService2.toString());
        Assert.isTrue(!result.contains(expectedService3), "Actual services list should NOT contain " + expectedService3.toString());
    }

    @Test
    public void convertMatrixToArrowsTest() {

        int[][] matrix = {new int[]{0, 10, 0}, new int[]{20, 0, 0}, new int[]{0, 0, 0}};

        Arrow expectedArrow1 = new Arrow(1, 2, 10);
        Arrow expectedArrow2 = new Arrow(2, 1, 20);
        Arrow expectedArrow3 = new Arrow(3, 1, 0);

        List<Arrow> arrows = MatrixAdapter.convertMatrixToArrows(matrix);
        Assert.isTrue(arrows.size() == 2, "Actual arrows list size should be equals 2");
        Assert.isTrue(arrows.contains(expectedArrow1), "Actual arrows list should contain " + expectedArrow1.toString());
        Assert.isTrue(arrows.contains(expectedArrow2), "Actual arrows list should contain " + expectedArrow2.toString());
        Assert.isTrue(!arrows.contains(expectedArrow3), "Actual arrows list should NOT contain " + expectedArrow3.toString());


    }
}
