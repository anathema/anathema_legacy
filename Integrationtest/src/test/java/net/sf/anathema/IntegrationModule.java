package net.sf.anathema;

import com.google.inject.AbstractModule;
import net.sf.anathema.characterengine.integration.EngineModule;

public class IntegrationModule extends AbstractModule {

  @Override
  protected void configure() {
    new EngineModule().configure(binder());
  }
}
