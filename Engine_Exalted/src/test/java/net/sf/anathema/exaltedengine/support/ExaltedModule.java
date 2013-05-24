package net.sf.anathema.exaltedengine.support;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import net.sf.anathema.exaltedengine.ExaltedEngine;

@SuppressWarnings("UnusedDeclaration")
public class ExaltedModule extends AbstractModule {
  @Override
  protected void configure() {
      bind(ExaltedEngine.class).in(Singleton.class);
      bind(CharacterHolder.class).in(Singleton.class);
  }
}