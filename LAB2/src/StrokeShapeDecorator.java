public class StrokeShapeDecorator extends ShapeDecorator {
    private String color;
    private double width;

    public StrokeShapeDecorator(Shape decoratedShape,String color, double width) {
        super(decoratedShape);
        this.color = color;
        this.width = width;
    }

    public String toSvg(String tag){
            String parentSvg = String.format("stroke=\"%s\" stroke-width=\"%f\"",color,width);
            if (!tag.isEmpty()){
                tag += " ";
            }
            tag += parentSvg;
        return decoratedShape.toSvg(parentSvg);
    }
}
