package net.sf.anathema.characterengine.integration;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import net.sf.anathema.characterengine.engine.DefaultEngine;
import net.sf.anathema.characterengine.engine.Engine;

public class EngineModule extends AbstractModule {
  @Override
  protected void configure() {
      bind(Engine.class).to(DefaultEngine.class).in(Singleton.class);
      bind(EngineCharacter.class).in(Singleton.class);
  }
}