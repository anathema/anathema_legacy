package net.sf.anathema.character.generic.modeltemplate;

import java.io.InputStream;

public interface TemplateLoader<T> {

  T load(InputStream inputStream);
}
