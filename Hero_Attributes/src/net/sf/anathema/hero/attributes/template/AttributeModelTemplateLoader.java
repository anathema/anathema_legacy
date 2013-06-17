package net.sf.anathema.hero.attributes.template;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.anathema.character.main.model.template.TemplateFactory;
import net.sf.anathema.character.main.model.template.TemplateLoader;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.SimpleIdentifier;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AttributeModelTemplateLoader implements TemplateLoader<AttributeTemplate> {

  // Code Snippet for Factory - as soon as model is initialized via smaller template
  public static AttributeTemplate loadTemplate(TemplateFactory templateFactory) {
    SimpleIdentifier templateId = new SimpleIdentifier("attributeDefault");
    return templateFactory.loadModelTemplate(templateId, new AttributeModelTemplateLoader());
  }

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
