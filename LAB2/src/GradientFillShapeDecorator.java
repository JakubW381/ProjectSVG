public class GradientFillShapeDecorator extends ShapeDecorator {
    private static int gradientIndex = 0;
    private Stop[] stops;

    public GradientFillShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
        stops = new Stop[10];
    }

    public void addStop(double offset, String color) {
        for (int i = 0; i < stops.length; i++) {
            if (stops[i] == null) {
                stops[i] = new Stop(offset, color);
                break;
            }
        }
    }

    public String toSvg() {

        gradientIndex++;


        StringBuilder gradientDef = new StringBuilder();
        gradientDef.append(String.format("\t<linearGradient id=\"g%d\">\n", gradientIndex));
        for (Stop stop : stops) {
            if (stop != null) {
                gradientDef.append(String.format("\t\t<stop offset=\"%f\" style=\"stop-color:%s\" />\n", stop.offset, stop.color));
            }
        }
        gradientDef.append("\t</linearGradient>");


        SvgScene.getInstance().addDefs(gradientDef.toString());
        String temp =String.format(" fill=\"url(#g%d)\"/>",gradientIndex);
       String parentSvg = super.toSvg(temp);
        return parentSvg;
    }
    
    private static class Stop {
        double offset;
        String color;

        public Stop(double offset, String color) {
            this.offset = offset;
            this.color = color;
        }
    }

    public static class Builder {
        private GradientFillShapeDecorator decorator;

        public Builder(Shape shape) {
            decorator = new GradientFillShapeDecorator(shape);
        }

        public Builder addStop(double offset, String color) {
            decorator.addStop(offset, color);
            return this;
        }

        public GradientFillShapeDecorator build() {
            return decorator;
        }
    }
}