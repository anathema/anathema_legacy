package net.sf.anathema.hero.sheet.pdf.encoder.boxes;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.environment.dependencies.DoNotInstantiateAutomatically;
import net.sf.anathema.hero.sheet.pdf.content.BasicContent;

@DoNotInstantiateAutomatically
public class DummyEncoderFactory implements EncoderFactory{

  private String id;

  public DummyEncoderFactory(String id) {
    this.id = id;
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }

  @Override
  public float getPreferredHeight(EncodingMetrics metrics, float width) {
    return 0; 
  }

  @Override
  public String getId() {
    return id;
  }
}
