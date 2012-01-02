package net.sf.anathema.character.reporting.pdf.rendering.boxes;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.resources.IResources;

public class BoxContentEncoderRegistry {

  public final MultiEntryMap<String, BoxContentEncoderFactory> factoryById = new MultiEntryMap<String, BoxContentEncoderFactory>();

  public void add(BoxContentEncoderFactory factory) {
    factoryById.add(factory.getId(), factory);
  }

  public boolean hasEncoder(String id, ReportContent content) {
    return !(findFactory(id, content) instanceof NullEncoderFactory);
  }

  public IBoxContentEncoder createEncoder(String id, IResources resource, ReportContent content) {
    return findFactory(id, content).create(resource, createBasicContent(content));
  }

  public boolean hasAttribute(String id, EncoderAttributeType type, ReportContent content) {
    return findFactory(id, content).hasAttribute(type);
  }

  public float getValue(String id, EncoderAttributeType type, ReportContent content) {
    return findFactory(id, content).getValue(createBasicContent(content), type);
  }

  private BoxContentEncoderFactory findFactory(String id, ReportContent content) {
    for (BoxContentEncoderFactory factory : factoryById.get(id)) {
      BasicContent basicContent = createBasicContent(content);
      if (factory.supports(basicContent)) {
        return factory;
      }
    }
    return new NullEncoderFactory(id);
  }

  private BasicContent createBasicContent(ReportContent content) {
    return content.createSubContent(BasicContent.class);
  }
}
