package net.sf.anathema.hero.sheet.pdf.encoder.boxes;

import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.lib.collection.MultiEntryMap;
import net.sf.anathema.lib.logging.Logger;

import java.util.Collection;

public class EncoderFactoryMap {
  private static final Logger logger = Logger.getLogger(EncoderFactoryMap.class);
  private final MultiEntryMap<String, EncoderFactory> factoryById = new MultiEntryMap<>();

  public EncoderFactoryMap(ObjectFactory objectFactory) {
    try {
      Collection<EncoderFactory> encoderFactories = objectFactory.instantiateAllImplementers(EncoderFactory.class);
      for (EncoderFactory factory : encoderFactories) {
        factoryById.add(factory.getId(), factory);
      }
    } catch (InitializationException e) {
      logger.error("Error instantiating encoder factory.", e);
    }
  }

  public EncoderFactory findFactory(ReportSession session, String... ids) {
    for (String id : ids) {
      for (EncoderFactory factory : factoryById.get(id)) {
        BasicContent basicContent = session.createContent(BasicContent.class);
        if (factory.supports(basicContent)) {
          return factory;
        }
      }
    }
    return new NullEncoderFactory(ids[0]);
  }
}