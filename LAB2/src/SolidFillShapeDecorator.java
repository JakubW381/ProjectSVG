public class SolidFillShapeDecorator extends ShapeDecorator{

    private String color;
    public SolidFillShapeDecorator(Shape decoratedShape, String color) {
        super(decoratedShape);
        this.color = color;
    }


    public String toSvg(String tag){
        //String pSvg = String.format("fill=\"%s\" %s ",color,string);
        String parentSvg = String.format("fill=\"%s\" %s ",color, tag);
        return decoratedShape.toSvg(parentSvg);
    }
}
