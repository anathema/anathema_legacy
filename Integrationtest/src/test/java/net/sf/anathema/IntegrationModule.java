package net.sf.anathema;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import net.sf.anathema.characterengine.integration.EngineModule;

public class IntegrationModule extends AbstractModule {

  @Override
  protected void configure() {
    new EngineModule().configure(binder());
    bind(CharacterHolder.class).in(Scopes.SINGLETON);
  }
}
