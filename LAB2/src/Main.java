import java.util.ArrayList;
import java.util.List;

public class Main{

    public static void main(String[] args)
    {
        Shape polygon = new Polygon(new Vec2[]{
                new Vec2(500, 100),
                new Vec2(220, 20),
                new Vec2(400, 25),
                new Vec2(70, 33),
        });
        Shape ellipse = new Ellipse(new Vec2(100, 200), 50.5, 75.7);
        ellipse = new SolidFillShapeDecorator(ellipse,"blue");

        ellipse = new TransformationDecorator.Builder()
                .rotate(1, new Vec2(0, 0))
                .trans(ellipse);

        ellipse = new DropShadowDecorator(ellipse);

        polygon = new GradientFillShapeDecorator.Builder()
                .setShape(polygon)
                .addStop(0, "red")
                .addStop(0.3, "blue")
                .addStop(1, "indigo")
                .build();

        TransformationDecorator.Builder builder = new TransformationDecorator.Builder();
        Shape s = builder
                .rotate(-50, new Vec2(0, 0))
                .translate(new Vec2(50, 60))
                .scale(new Vec2(1, 1.5))
                .trans(new Ellipse(new Vec2(40, 50), 20, 40));

        SvgScene scene = SvgScene.getInstance();

        scene.addShape(polygon);
        scene.addShape(ellipse);
        scene.addShape(s);

        scene.save("out.html");
    }
}