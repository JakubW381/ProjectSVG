public class SolidFilledPolygon extends Polygon {

    private String color;

    public SolidFilledPolygon(Vec2[] points, String color) {
        super(points);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toSvg(String string){
        String pSvg = String.format("fill=\"%s\" %s ",color,string);
        String parentSvg = super.toSvg(pSvg);
        return parentSvg;
    }


}
