public class TransformationDecorator extends ShapeDecorator {

    String transform;

    public TransformationDecorator(Shape shape, String transform) {
        super(shape);
        this.transform = transform;
    }

    public static class Builder{

        private Shape shape;
        String transform;

        public Builder(Shape shape) {
            this.shape = shape;
            this.transform = "";
        }

        public Builder translate(Vec2 translateVector){
            transform += String.format("translate(%f %f) ",translateVector.x,translateVector.y);
            return this;
        }
        public Builder rotate(double rotateAngle, Vec2 rotateCenter){
            transform += String.format("rotate(%f %f %f) ",rotateAngle,rotateCenter.x,rotateCenter.y);
            return this;
        }
        public Builder scale(Vec2 scaleVector){
            transform += String.format("scale(%f %f) ",scaleVector.x,scaleVector.y);
            return this;
        }
        public  TransformationDecorator trans(){
            return new TransformationDecorator(shape,transform);
        }
    }

    @Override
    public String toSvg(String tag){
        String parentSvg = String.format("transform=\"%s\" %s",tag,this.transform);
        return super.toSvg(parentSvg);
    }
}
