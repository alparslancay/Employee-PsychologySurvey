package presentationLayer;

import ChartDirector.ChartViewer;
import ChartDirector.PolarChart;
import entityLayer.EmployeeEvaluate;
import java.util.List;

public class RadarChart implements DemoModule {
    public String toString() { return chartName; }
    
    List<EmployeeEvaluate> evaluates;
    String chartName;
    public RadarChart(List<EmployeeEvaluate> evaluates, String chartName){
        this.evaluates = evaluates;
        this.chartName = chartName;
    }
    //Number of charts produced in this demo
    public int getNoOfCharts() { return 1; }

    //Main code for creating charts
    public void createChart(ChartViewer viewer, int chartIndex)
    {
        
        // The data for the chart
        double[] data = new double[evaluates.size()];

        // The labels for the chart
        String[] labels = new String[evaluates.size()];
        
        for (int evaluateRecorder = 0; evaluateRecorder < evaluates.size(); evaluateRecorder++) {
            data[evaluateRecorder] = evaluates.get(evaluateRecorder).pointValue*20;
            labels[evaluateRecorder] = evaluates.get(evaluateRecorder).questionText;
        }

        // Create a PolarChart object of size 450 x 350 pixels
        PolarChart c = new PolarChart(800, 600);
        
        // Set center of plot area at (225, 185) with radius 150 pixels
        c.setPlotArea(400, 300, 150);

        // Add an area layer to the polar chart
        c.addAreaLayer(data, 0x9999ff);

        // Set the labels to the angular axis as spokes
        c.angularAxis().setLabels(labels);

        // Output the chart
        viewer.setChart(c);
        
        //include tool tip for the chart
        viewer.setImageMap(c.getHTMLImageMap("clickable", "", "title='{label}: score = {value}'"));
    }

    //Allow this module to run as standalone program for easy testing
}
