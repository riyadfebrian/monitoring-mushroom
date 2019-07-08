package pkl.com.sistemmonitoringjamur;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.macroyau.thingspeakandroid.ThingSpeakChannel;
import com.macroyau.thingspeakandroid.ThingSpeakLineChart;
import com.macroyau.thingspeakandroid.model.ChannelFeed;

import java.util.Calendar;
import java.util.Date;

import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class MainActivity extends AppCompatActivity  {
    private final static int INTERVAL = 500; //2 minutes
    private ThingSpeakChannel tsChannel;
    private ThingSpeakLineChart tsChart, tsChart2, tsChart3;
    private LineChartView chartView, chartView2, chartView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tsChannel = new ThingSpeakChannel(<Channel-ID>, "<ReadAPIKey>");

        tsChannel.setChannelFeedUpdateListener(new ThingSpeakChannel.ChannelFeedUpdateListener() {
            @Override
            public void onChannelFeedUpdated(long channelId, String channelName, ChannelFeed channelFeed) {
                // Show Channel ID and name on the Action Bar
                getSupportActionBar().setTitle(channelName);
                getSupportActionBar().setSubtitle("Channel " + channelId);
                // Notify last update time of the Channel feed through a Toast message
//                Date lastUpdate = channelFeed.getChannel().getUpdatedAt();
//                Toast.makeText(MainActivity.this, lastUpdate.toString(), Toast.LENGTH_LONG).show();
            }
        });
        // Fetch the specific Channel feed
        tsChannel.loadChannelFeed();


        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -5);


        chartView = findViewById(R.id.chart);
        chartView.setZoomEnabled(false);
        chartView.setValueSelectionEnabled(true);

        chartView2 = findViewById(R.id.chart2);
        chartView2.setZoomEnabled(false);
        chartView2.setValueSelectionEnabled(true);

        chartView3 = findViewById(R.id.chart3);
        chartView3.setZoomEnabled(false);
        chartView3.setValueSelectionEnabled(true);

        // Create a line chart from Field1 of ThinkSpeak Channel 9
        tsChart = new ThingSpeakLineChart(608339, 1, "O0CG9IK1XSX40IS2");
        // Get 200 entries at maximum
        tsChart.setNumberOfEntries(10);
        // Set value axis labels on 10-unit interval
        tsChart.setValueAxisLabelInterval(10);
        // Set date axis labels on 5-minute interval
        tsChart.setDateAxisLabelInterval(1);
        // Show the line as a cubic spline
        tsChart.useSpline(true);
        // Set the line color
        tsChart.setLineColor(Color.parseColor("#D32F2F"));
        // Set the axis color
        tsChart.setAxisColor(Color.parseColor("#455a64"));
        // Set the starting date (5 minutes ago) for the default viewport of the chart
        tsChart.setChartStartDate(calendar.getTime());
        // Set listener for chart data update
        tsChart.setListener(new ThingSpeakLineChart.ChartDataUpdateListener() {
            @Override
            public void onChartDataUpdated(long channelId, int fieldId, String title, LineChartData lineChartData, Viewport maxViewport, Viewport initialViewport) {
                // Set chart data to the LineChartView
                chartView.setLineChartData(lineChartData);
                // Set scrolling bounds of the chart
                chartView.setMaximumViewport(maxViewport);
                // Set the initial chart bounds
                chartView.setCurrentViewport(initialViewport);
            }
        });
        // Load chart data asynchronously



        // Create a line chart from Field1 of ThinkSpeak Channel 9
        tsChart2 = new ThingSpeakLineChart(608339, 2, "O0CG9IK1XSX40IS2");
        // Get 200 entries at maximum
        tsChart2.setNumberOfEntries(10);
        // Set value axis labels on 10-unit interval
        tsChart2.setValueAxisLabelInterval(10);
        // Set date axis labels on 5-minute interval
        tsChart2.setDateAxisLabelInterval(1);
        // Show the line as a cubic spline
        tsChart2.useSpline(true);
        // Set the line color
        tsChart2.setLineColor(Color.parseColor("#D32F2F"));
        // Set the axis color
        tsChart2.setAxisColor(Color.parseColor("#455a64"));
        // Set the starting date (5 minutes ago) for the default viewport of the chart
        tsChart2.setChartStartDate(calendar.getTime());
        // Set listener for chart data update
        tsChart2.setListener(new ThingSpeakLineChart.ChartDataUpdateListener() {
            @Override
            public void onChartDataUpdated(long channelId, int fieldId, String title, LineChartData lineChartData, Viewport maxViewport, Viewport initialViewport) {
                // Set chart data to the LineChartView
                chartView2.setLineChartData(lineChartData);
                // Set scrolling bounds of the chart
                chartView2.setMaximumViewport(maxViewport);
                // Set the initial chart bounds
                chartView2.setCurrentViewport(initialViewport);
            }
        });
        // Load chart data asynchronously






        // Create a line chart from Field1 of ThinkSpeak Channel 9
        tsChart3 = new ThingSpeakLineChart(608339, 3, "O0CG9IK1XSX40IS2");
        // Get 200 entries at maximum
        tsChart3.setNumberOfEntries(10);
        // Set value axis labels on 10-unit interval
        tsChart3.setValueAxisLabelInterval(10);
        // Set date axis labels on 5-minute interval
        tsChart3.setDateAxisLabelInterval(1);
        // Show the line as a cubic spline
        tsChart3.useSpline(true);
        // Set the line color
        tsChart3.setLineColor(Color.parseColor("#D32F2F"));
        // Set the axis color
        tsChart3.setAxisColor(Color.parseColor("#455a64"));
        // Set the starting date (5 minutes ago) for the default viewport of the chart
        tsChart3.setChartStartDate(calendar.getTime());
        // Set listener for chart data update
        tsChart3.setListener(new ThingSpeakLineChart.ChartDataUpdateListener() {
            @Override
            public void onChartDataUpdated(long channelId, int fieldId, String title, LineChartData lineChartData, Viewport maxViewport, Viewport initialViewport) {
                // Set chart data to the LineChartView
                chartView3.setLineChartData(lineChartData);
                // Set scrolling bounds of the chart
                chartView3.setMaximumViewport(maxViewport);
                // Set the initial chart bounds
                chartView3.setCurrentViewport(initialViewport);
            }
        });
        // Load chart data asynchronously

        final Handler mHandler = new Handler();

        final Runnable handleTask = new Runnable() {
            @Override
            public void run() {
                tsChart.loadChartData();
                tsChart2.loadChartData();
                tsChart3.loadChartData();
                mHandler.postDelayed(this, 1000);
            }
        };

            handleTask.run();

    }


}
