package net.sf.anathema.hero.template;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.anathema.lib.exception.PersistenceException;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConfigurableTemplateLoader<T> implements TemplateLoader<T> {

  private final Gson gson;
  private final Class<T> aClass;

  public ConfigurableTemplateLoader(Class<T> aClass) {
    this.aClass = aClass;
    GsonBuilder gsonBuilder = new GsonBuilder();
    gson = gsonBuilder.create();

  }

  public T load(InputStream inputStream) {
    try {
      ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
      IOUtils.copy(inputStream, byteStream);
      String json = new String(byteStream.toByteArray());
      return gson.fromJson(json, aClass);
    } catch (IOException e) {
      throw new PersistenceException(e);
    }
  }
}