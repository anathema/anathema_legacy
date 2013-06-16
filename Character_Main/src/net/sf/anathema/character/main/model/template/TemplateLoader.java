package net.sf.anathema.character.main.model.template;

import java.io.InputStream;

public interface TemplateLoader<T> {

  T load(InputStream inputStream);
}
