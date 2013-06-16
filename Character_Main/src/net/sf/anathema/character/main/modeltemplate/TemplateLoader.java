package net.sf.anathema.character.main.modeltemplate;

import java.io.InputStream;

public interface TemplateLoader<T> {

  T load(InputStream inputStream);
}
