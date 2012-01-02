package net.sf.anathema.character.reporting.pdf.rendering.boxes;

import java.util.HashMap;
import java.util.Map;

public class BoxContentEncoderRegistry {

  public final Map<String, BoxContentEncoderFactory> factoryById = new HashMap<String, BoxContentEncoderFactory>();

  public void add(BoxContentEncoderFactory factory) {
    factoryById.put(factory.getId(), factory);
  }

  public BoxContentEncoderFactory getById(String id) {
    return factoryById.get(id);
  }
}
