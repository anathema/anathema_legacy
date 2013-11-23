package net.sf.anathema.character.main.view;

import net.sf.anathema.framework.environment.dependencies.DoNotInstantiateAutomatically;
import net.sf.anathema.framework.util.Produces;

@DoNotInstantiateAutomatically
@Produces(DummyView.class)
public class ConfigurableViewFactory implements SubViewFactory {
  private DummyView view;

  public ConfigurableViewFactory(DummyView view) {
    this.view = view;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T create() {
    return (T) view;
  }
}