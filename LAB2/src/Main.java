import java.util.ArrayList;
import java.util.List;

public class Main{

    public static void main(String[] args)
    {
        Shape polygon = new Polygon(new Vec2[]{
                new Vec2(200,100),
                new Vec2(200,200),
                new Vec2(300,100),
        });
        Shape rectangle = new Polygon(new Vec2[]{
                new Vec2(200,100),
                new Vec2(200,200),
                new Vec2(300,100),});

        polygon = new SolidFillShapeDecorator(polygon,"green");
        polygon = new StrokeShapeDecorator(polygon,"blue",3);
        polygon = new DropShadowDecorator(polygon);

        GradientFillShapeDecorator.Builder gradientBuilder2 = new GradientFillShapeDecorator.Builder(rectangle);
        gradientBuilder2.addStop(0, "yellow").addStop(1, "green");
        GradientFillShapeDecorator gradientRectangle = gradientBuilder2.build();

        DropShadowDecorator shadowRectangle = new DropShadowDecorator(rectangle);

        Shape ellipse = new Ellipse(new Vec2(100, 400), 50, 100);
        TransformationDecorator.Builder builder = new TransformationDecorator.Builder(
                new SolidFillShapeDecorator(ellipse,"red"));
        Shape s =
                builder
                .rotate(-25, new Vec2(0, 0))
                .translate(new Vec2(20, 20))
                .scale(new Vec2(1.5, 1.5))
                .trans();

        SvgScene scene = SvgScene.getInstance();
        scene.addShape(polygon);
        scene.addShape(ellipse);
        scene.addShape(s);
        scene.addShape(gradientRectangle);
        scene.addShape(shadowRectangle);

        scene.addDefs("\t<filter id=\"f%d\" x=\"-100%%\" y=\"-100%%\" width=\"300%%\" height=\"300%%\">\n" +
                "\t\t<feOffset result=\"offOut\" in=\"SourceAlpha\" dx=\"5\" dy=\"5\" />\n" +
                "\t\t<feGaussianBlur result=\"blurOut\" in=\"offOut\" stdDeviation=\"5\" />\n" +
                "\t\t<feBlend in=\"SourceGraphic\" in2=\"blurOut\" mode=\"normal\" />\n" +
                "\t</filter>");
        scene.addDefs("\t<linearGradient id=\"g%d\">\n");

        scene.save("./out.html");
    }
}