package net.sf.anathema.character.reporting.pdf.rendering.boxes;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;

import java.util.Collection;

public class EncoderRegistry {

  private static final Logger logger = Logger.getLogger(EncoderRegistry.class);
  private final MultiEntryMap<String, EncoderFactory> factoryById = new MultiEntryMap<String, EncoderFactory>();
  private Instantiater instantiater;

  public EncoderRegistry(Instantiater instantiater) {
    this.instantiater = instantiater;
  }

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
      for (EncoderFactory factory : getFactoriesByIdViaReflection().get(id)) {
        BasicContent basicContent = createBasicContent(content);
        if (factory.supports(basicContent)) {
          return factory;
        }
      }
    }
    return new NullEncoderFactory(ids[0]);
  }

  private MultiEntryMap<String, EncoderFactory> getFactoriesByIdWithoutReflection() {
    return factoryById;
  }

  private MultiEntryMap<String, EncoderFactory> getFactoriesByIdViaReflection() {
    MultiEntryMap<String, EncoderFactory> factoryById = new MultiEntryMap<String, EncoderFactory>();
    try {
      Collection<EncoderFactory> encoderFactories = instantiater.instantiateAll(RegisteredEncoderFactory.class);
      for (EncoderFactory factory : encoderFactories) {
        factoryById.add(factory.getId(), factory);
      }
    } catch (InitializationException e) {
      logger.error("Error instantiating encoder factory.", e);
    }
    return factoryById;
  }

  private BasicContent createBasicContent(ReportContent content) {
    return content.createSubContent(BasicContent.class);
  }
}
