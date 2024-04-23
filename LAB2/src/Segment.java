public class Segment {
    private Vec2 begin;
    private Vec2 end;

    Segment(Vec2 begin, Vec2 end){
        this.begin = begin;
        this.end = end;
    }

    public Vec2 getBegin() {
        return begin;
    }

    public Vec2 getEnd() {
        return end;
    }

    public String toSVG(){
    StringBuilder svgCode = new StringBuilder();
    svgCode.append("<svg width=\"100\" height=\"100\">");
    svgCode.append(String.format("<line x1='%f' y1='%f' x2='%f' y2='%f' stroke='black'/>", begin.x, begin.y, end.x, end.y));
    svgCode.append("</svg>");
    return svgCode.toString();
    }
    public static Segment[] perpedicularSegment(Segment seg, Vec2 point){
    double dx = seg.end.x - seg.begin.x;
    double dy = seg.end.y - seg.begin.y;
    double newDx1 = -dy;
    double newDy1 = dx;
    double newDx2 = dy;
    double newDy2 = -dx;
    double endX1 =  point.x + newDx1;
    double endY1 =  point.x + newDy1;
    double endX2 =  point.x + newDx2;
    double endY2 =  point.x + newDy2;

    Vec2 first_end = new Vec2(endX1,endY1);
    Vec2 second_end = new Vec2(endX2,endY2);

    Segment first = new Segment(point, first_end);
    Segment second =new Segment(point, second_end);
    return new Segment[]{first,second};
    }

    public double segLength(){
        return Math.sqrt(Math.pow(begin.x - end.x,2) + Math.pow(begin.y - end.y,2));
    }

    public static Segment perpedicularSegment(Segment seg, double dist){
        double dx = seg.begin.x - seg.end.x;
        double dy = seg.begin.y - seg.end.y;

        double lengthOfDiagonal = Math.sqrt(dx * dx + dy * dy);
        double normalizedDx = dx / lengthOfDiagonal;
        double normalizedDy = dy / lengthOfDiagonal;

        double newDx1 = -normalizedDy * dist/2;
        double newDy1 = normalizedDx * dist/2;
        double newDx2 = normalizedDy * dist/2;
        double newDy2 = -normalizedDx * dist/2;

        double sx = (seg.begin.x + seg.end.x) / 2;
        double sy = (seg.begin.y + seg.end.y) / 2;

        double begin1 = sx + newDx1;
        double end1 = sy + newDy1;
        double begin2 = sx + newDx2;
        double end2 = sy + newDy2;

        Vec2 p1 = new Vec2(begin1,end1);
        Vec2 p2 = new Vec2(begin2,end2);
        return new Segment(p1,p2);
    }
}
