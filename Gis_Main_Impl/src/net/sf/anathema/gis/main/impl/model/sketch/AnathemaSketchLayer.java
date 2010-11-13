package net.sf.anathema.gis.main.impl.model.sketch;

import de.disy.gis.gisterm.map.layer.edit.ILayerGraphicsEditStrategy;
import de.disy.gis.gisterm.map.layer.sketch.AbstractSketchLayer;
import de.disy.gis.gisterm.map.layer.sketch.ISketchPropertiesFactory;
import de.disy.gis.gisterm.map.layer.sketch.ProtoTypeSketchPropertiesFactory;
import de.disy.gis.gisterm.map.layer.sketch.SketchObjectFactory;
import de.disy.gisterm.pro.sketchlayer.edit.SketchLayerGraphicsEditStrategy;

public class AnathemaSketchLayer extends AbstractSketchLayer {

  public AnathemaSketchLayer() {
    ISketchPropertiesFactory propertiesFactory = new ProtoTypeSketchPropertiesFactory(new ImageFileProvider());
    setSketchObjectFactory(new SketchObjectFactory(propertiesFactory));
  }

  @Override
  protected ILayerGraphicsEditStrategy createGraphicsEditStrategy() {
    return new SketchLayerGraphicsEditStrategy(getGraphicsObjectList());
  }
}