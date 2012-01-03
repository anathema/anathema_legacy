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
    return !(findFactory(content, id) instanceof NullEncoderFactory);
  }

  public IBoxContentEncoder createEncoder(IResources resource, ReportContent content, String... ids) {
    return findFactory(content, ids).create(resource, createBasicContent(content));
  }

  public boolean hasAttribute(EncoderAttributeType type, ReportContent content, String id) {
    return findFactory(content, id).hasAttribute(type);
  }

  public float getValue(EncoderAttributeType type, ReportContent content, String id) {
    return findFactory(content, id).getValue(createBasicContent(content), type);
  }

  private BoxContentEncoderFactory findFactory(ReportContent content, String... ids) {
    for (String id : ids) {
      for (BoxContentEncoderFactory factory : factoryById.get(id)) {
        BasicContent basicContent = createBasicContent(content);
        if (factory.supports(basicContent)) {
          return factory;
        }
      }
    }
    return new NullEncoderFactory(ids[0]);
  }

  private BasicContent createBasicContent(ReportContent content) {
    return content.createSubContent(BasicContent.class);
  }
}
