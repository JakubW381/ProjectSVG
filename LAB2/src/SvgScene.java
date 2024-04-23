import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SvgScene {
    private List<Shape> shapes = new ArrayList<>();
    private static SvgScene instance = null;
    private static int index;
    private List<String> defs = new ArrayList<>();

    public static SvgScene getInstance() {
        if(instance == null){
            instance = new SvgScene();
        }
        return instance;
    }
    public void addDefs(String def){ defs.add(String.format(def,index)); index++; }

    public void addShape(Shape shape) {
        this.shapes.add(shape);
    }
    public void save(String path){
    StringBuilder html = new StringBuilder();
    html.append("<!DOCTYPE html>\n<html>\n<body>\n<svg width=\"1024\" height=\"768\" xmlns=\"http://www.w3.org/2000/svg\">\n");
    html.append("<defs>\n");

//        String filterCode = String.format("\t<filter id=\"f%d\" x=\"-100%%\" y=\"-100%%\" width=\"300%%\" height=\"300%%\">\n" +
//                "\t\t<feOffset result=\"offOut\" in=\"SourceAlpha\" dx=\"5\" dy=\"5\" />\n" +
//                "\t\t<feGaussianBlur result=\"blurOut\" in=\"offOut\" stdDeviation=\"5\" />\n" +
//                "\t\t<feBlend in=\"SourceGraphic\" in2=\"blurOut\" mode=\"normal\" />\n" +
//                "\t</filter>", index);

        for (String def : defs){
            html.append(def);
        }

        html.append("</defs>\n");

        for (Shape shape : shapes){
            html.append("\t").append(shape.toSvg("")).append("\n");
        }
        html.append("</svg>\n</body>\n</html>");
        try(FileWriter writer = new FileWriter(path)) {
            writer.write(html.toString());
        }catch (IOException e){
            e.printStackTrace();
        }
    }



}
