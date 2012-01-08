package net.sf.anathema.character.reporting.pdf.rendering.boxes;

import net.sf.anathema.character.generic.framework.CharacterReflections;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.logging.Logger;
import net.sf.anathema.lib.resources.IResources;

import java.util.Set;

import static java.text.MessageFormat.format;

public class EncoderRegistry {

  private static final Logger logger = Logger.getLogger(EncoderRegistry.class);
  private final MultiEntryMap<String, EncoderFactory> factoryById = new MultiEntryMap<String, EncoderFactory>();
  private CharacterReflections reflections;

  public EncoderRegistry(CharacterReflections reflections) {
    this.reflections = reflections;
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
      for (EncoderFactory factory : getFactoriesByIdWithoutReflection().get(id)) {
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
    Set<Class<?>> encoderFactoryClasses = reflections.getTypesAnnotatedWith(RegisteredEncoderFactory.class);
    for (Class<?> factoryClass : encoderFactoryClasses) {
      addFactory(factoryById, factoryClass);
    }
    return factoryById;
  }

  private void addFactory(MultiEntryMap<String, EncoderFactory> factoryById, Class<?> factoryClass) {
    try {
      EncoderFactory factory = (EncoderFactory) factoryClass.newInstance();
      factoryById.add(factory.getId(), factory);
    } catch (Exception e) {
      logger.error(format("Error instantiating encoder factory for class '{0}'.", factoryClass.getName()), e);
    }
  }

  private BasicContent createBasicContent(ReportContent content) {
    return content.createSubContent(BasicContent.class);
  }
}
