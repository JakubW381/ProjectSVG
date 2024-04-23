public class Polygon implements Shape {

    private Vec2[] points;
    Style style;

    public Polygon(Vec2[] points) {
        style = new Style("none", "black", 1.0);
        this.points = points;
    }

    public Polygon(Vec2[] points, Style style) {
        this.style = style;
        this.points = points;
    }
    Polygon(Polygon copy){
        style = new Style("none", "black", 1.0);
        this.points = copy.points;
    }

    public String toSvg() {
        StringBuilder poly = new StringBuilder();
        poly.append("<polygon points=\"");
        for(Vec2 point: points){
            poly.append(point.x).append(",").append(point.y).append(" ");
        }
        poly.append("\" style=\"fill:none;stroke:black;stroke-width:1\"/>\n");
        return poly.toString();
    }

    @Override
    public String toSvg(String tag) { //<polygon points=\"%s\" %s />
        StringBuilder poly = new StringBuilder();
        poly.append("<polygon points='");
        for(Vec2 point: points){
            poly.append(point.x).append(",").append(point.y).append(" ");
        }
        poly.append("' ").append(tag).append(" />");//poly.append(String.format("fill:none;stroke:black;stroke-width:1\"/>\n"));
        return poly.toString();
    }

    public static Polygon square(Segment seg){

    Segment segP = Segment.perpedicularSegment(seg,seg.segLength());
    Vec2 P1 = seg.getBegin();
    Vec2 P2 = segP.getBegin();
    Vec2 P3 = seg.getEnd();
    Vec2 P4 = segP.getEnd();
    Vec2[] points = new Vec2[]{P1,P2,P3,P4};
    return new Polygon(points);

    }

}
