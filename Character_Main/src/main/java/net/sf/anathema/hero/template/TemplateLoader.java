package net.sf.anathema.hero.template;

import java.io.InputStream;

public interface TemplateLoader<T> {

  T load(InputStream inputStream);
}
