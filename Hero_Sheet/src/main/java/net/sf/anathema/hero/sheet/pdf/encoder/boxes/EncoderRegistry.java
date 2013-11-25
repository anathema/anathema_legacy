package net.sf.anathema.hero.sheet.pdf.encoder.boxes;

import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;
import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.Resources;

public class EncoderRegistry {

  private final EncoderFactoryMap map;

  public EncoderRegistry(ObjectFactory objectFactory) {
    this.map = new EncoderFactoryMap(objectFactory);
  }

  public boolean hasEncoder(String id, ReportSession session) {
    return !(map.findFactory(session, id) instanceof NullEncoderFactory);
  }

  public ContentEncoder createEncoder(Resources resource, ReportSession session, String... ids) {
    return map.findFactory(session, ids).create(resource, session.createContent(BasicContent.class));
  }

  public float getPreferredHeight(EncodingMetrics metrics, float width, String... ids) {
    return map.findFactory(metrics.getSession(), ids).getPreferredHeight(metrics, width);
  }

}
