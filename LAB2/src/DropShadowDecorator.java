public class DropShadowDecorator extends ShapeDecorator{

    public DropShadowDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }


    public String toSvg(int index) {
        return decoratedShape.toSvg(String.format(" filter=\"url(#f%d)\"/>", index));
    }
}
