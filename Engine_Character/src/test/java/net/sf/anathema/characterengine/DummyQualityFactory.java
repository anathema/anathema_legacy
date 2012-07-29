package net.sf.anathema.characterengine;

public class DummyQualityFactory implements QualityFactory {
  @Override
  public Quality create() {
    return new DummyQuality();
  }
}