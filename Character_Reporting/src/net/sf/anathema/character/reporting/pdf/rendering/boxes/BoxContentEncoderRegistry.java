package net.sf.anathema.character.reporting.pdf.rendering.boxes;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.NullBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.resources.IResources;

public class BoxContentEncoderRegistry {

  public static final NullBoxContentEncoder NULL_ENCODER = new NullBoxContentEncoder("Unknown");
  public final MultiEntryMap<String, BoxContentEncoderFactory> factoryById = new MultiEntryMap<String, BoxContentEncoderFactory>();

  public void add(BoxContentEncoderFactory factory) {
    factoryById.add(factory.getId(), factory);
  }

  public IBoxContentEncoder createEncoder(String id, IResources resource, ReportContent content) {
    for (BoxContentEncoderFactory factory : factoryById.get(id)) {
      if (factory.supports(content.createSubContent(BasicContent.class))) {
        return factory.create(resource);
      }
    }
    return NULL_ENCODER;
  }
}
