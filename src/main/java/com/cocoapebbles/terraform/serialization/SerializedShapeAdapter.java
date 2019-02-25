package com.cocoapebbles.terraform.serialization;

public class SerializedShapeAdapter {
      /*  public static CubeDimensions deserializeCubeDimensions(List<Integer> dimensions){
        return new CubeDimensions(dimensions.get(0), dimensions.get(1), dimensions.get(2));
    }

    public static List<Integer> serializeCubeDimensions(CubeDimensions dimensions){
        List<Integer> dimension = new ArrayList<>();
        dimension.add(dimensions.getHeightY());
        dimension.add(dimensions.getWidthZ());
        dimension.add(dimensions.getLengthX());
        return dimension;
    }
*/



    public static SerializedShapeResourceData serializedShapeResourceData(ShapeResourceData shapeResourceData){
        SerializedShapeResourceData serializedShapeResourceData = new SerializedShapeResourceData();
        serializedShapeResourceData.setCurrentData(shapeResourceData.);
    }
    /*
    public static CylinderDimensions deserializeCylinderDimensions(List<Integer> dimensions) {
        return new CylinderDimensions(dimensions.get(0), dimensions.get(1));
    }

    public static List<Integer> serializeCylinderDimensions(CylinderDimensions dimensions) {
        List<Integer> dimension = new ArrayList<>();
        dimension.add(dimensions.getRadius());
        dimension.add(dimensions.getHeight());
        return dimension;
    }*/
}
