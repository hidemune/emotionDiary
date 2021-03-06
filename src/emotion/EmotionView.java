package emotion;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static emotion.Emotion.anchor;
import static emotion.Emotion.keys;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Random;

import org.jzy3d.analysis.AbstractAnalysis;
import org.jzy3d.analysis.AnalysisLauncher;
import org.jzy3d.chart.factories.AWTChartComponentFactory;
import org.jzy3d.colors.Color;
import org.jzy3d.colors.ColorMapper;
import org.jzy3d.colors.colormaps.ColorMapRainbow;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.maths.Rectangle;
import org.jzy3d.plot3d.primitives.ConcurrentScatterMultiColorList;
import org.jzy3d.plot3d.rendering.canvas.Quality;
import org.jzy3d.plot3d.rendering.lights.Light;
import org.jzy3d.plot3d.text.drawable.DrawableTextBitmap;
import org.jzy3d.plot3d.transform.space.SpaceTransformer;

public class EmotionView extends AbstractAnalysis {
    public static Coord3d lgh;
    private emotionJFrame mainFrm;
    private ArrayList<SpaceTransformer> transformers = new ArrayList();
    private DrawableTextBitmap bit;
    private Light light;
    public EmotionView(final emotionJFrame mainFrm) {
        this.mainFrm = mainFrm;
    }
    public void main() throws Exception {
        //EmotionView frm = new EmotionView(this.mainFrm);
        //frm.init();
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        //int width = gd.getDisplayMode().getWidth();
        //int height = gd.getDisplayMode().getHeight();
        AnalysisLauncher.open(this,
                new Rectangle(mainFrm.getWidth()/2, mainFrm.getHeight()/2,
                        mainFrm.getWidth()/2, mainFrm.getHeight()/2));
        // */
        
        /*
        frm.chart.startAnimator();
        frm.chart.addMouseCameraController();
        frm.chart.addMousePickingController(10);
        frm.chart.addKeyboardCameraController();
        frm.chart.addKeyboardScreenshotController();
        frm.chart.show(new Rectangle(mainFrm.getWidth() - mainFrm.getWidth()/2, mainFrm.getY()/2,
                        mainFrm.getWidth()/2, mainFrm.getHeight()/2), "感情のグラフ");
        */
    }

    @Override
    public void init() {
        
        
        float x;
        float y;
        float z;
        float a;

        ArrayList<Coord3d> points = new ArrayList<Coord3d>();
        Color[] colors = new Color[keys.size()];

        Random r = new Random();
        r.setSeed(0);

        for (int i = 0; i < keys.size(); i++) {
            x = Float.valueOf("" + keys.get(i).x);
            y = Float.valueOf("" + keys.get(i).y);
            z = Float.valueOf("" + keys.get(i).z);
            points.add(new Coord3d(x, y, z));
            a = 0.25f;
            colors[i] = new Color(x, y, z, a);
        }
        /*
                http://doc.jzy3d.org/javadoc/1.0.0/jzy3d-api/index.html
         */

        ConcurrentScatterMultiColorList scatter = new ConcurrentScatterMultiColorList(points, new ColorMapper(new ColorMapRainbow(), 0.1, 1.1, new Color(1, 1, 1, .5f)));
        chart = AWTChartComponentFactory.chart(Quality.Nicest, "awt");
        Light.resetCounter();
        lgh = new Coord3d(Emotion.xxx,Emotion.yyy,Emotion.zzz);
        light = chart.addLight(lgh, Color.BLACK, Color.BLUE, Color.YELLOW, 0.1f);
        chart.getView();
        chart.getScene().add(scatter);

        chart.getView().setViewPoint(new Coord3d(-0.2f, -0.5f, 0.1f), true);
        //chart.addLight(new Coord3d(1,1,1));
        for (int i = 0; i < anchor.size(); i++) {

            bit = new DrawableTextBitmap(anchor.get(i).wordEng, new Coord3d(anchor.get(i).x, anchor.get(i).y, anchor.get(i).z), Color.BLUE);
            chart.addDrawable(bit);

        }

    }
    
    public void dispose() {
        mainFrm.setViewButton(true);
        chart.getScene().remove(light);
        lgh = null;
        chart.dispose();
        chart = null;
    };



    
}
