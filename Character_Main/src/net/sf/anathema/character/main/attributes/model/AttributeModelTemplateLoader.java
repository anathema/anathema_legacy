package net.sf.anathema.character.main.attributes.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.anathema.character.generic.modeltemplate.TemplateLoader;
import net.sf.anathema.character.main.attributes.template.AttributeTemplate;
import net.sf.anathema.lib.exception.PersistenceException;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AttributeModelTemplateLoader implements TemplateLoader<AttributeTemplate> {

  private final Gson gson;

  public AttributeModelTemplateLoader() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setPrettyPrinting();
    gson = gsonBuilder.create();

  }

  @Override
  public AttributeTemplate load(InputStream inputStream) {
    try {
      ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
      IOUtils.copy(inputStream, byteStream);
      String json = new String(byteStream.toByteArray());
      return gson.fromJson(json, AttributeTemplate.class);
    } catch (IOException e) {
      throw new PersistenceException(e);
    }
  }
}
