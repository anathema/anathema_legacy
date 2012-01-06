package net.sf.anathema.character.reporting.pdf.rendering.boxes;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.resources.IResources;

public class EncoderRegistry {

  public final MultiEntryMap<String, EncoderFactory> factoryById = new MultiEntryMap<String, EncoderFactory>();

  public void add(EncoderFactory factory) {
    factoryById.add(factory.getId(), factory);
  }

  public boolean hasEncoder(String id, ReportContent content) {
    return !(findFactory(content, id) instanceof NullEncoderFactory);
  }

  public ContentEncoder createEncoder(IResources resource, ReportContent content, String... ids) {
    return findFactory(content, ids).create(resource, createBasicContent(content));
  }

  public boolean hasAttribute(EncoderAttributeType type, ReportContent content, String id) {
    return findFactory(content, id).hasAttribute(type);
  }

  public float getValue(EncoderAttributeType type, EncodingMetrics metrics, String... ids) {
    return findFactory(metrics.getContent(), ids).getValue(metrics, type);
  }

  private EncoderFactory findFactory(ReportContent content, String... ids) {
    for (String id : ids) {
      for (EncoderFactory factory : factoryById.get(id)) {
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
