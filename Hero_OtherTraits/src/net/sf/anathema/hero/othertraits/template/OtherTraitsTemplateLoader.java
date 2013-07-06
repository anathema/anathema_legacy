package net.sf.anathema.hero.othertraits.template;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.template.TemplateLoader;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.SimpleIdentifier;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class OtherTraitsTemplateLoader implements TemplateLoader<OtherTraitsTemplate> {

  public static OtherTraitsTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    SimpleIdentifier templateId = new SimpleIdentifier(templateName);
    return templateFactory.loadModelTemplate(templateId, new OtherTraitsTemplateLoader());
  }

  private final Gson gson;

  public OtherTraitsTemplateLoader() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setPrettyPrinting();
    gson = gsonBuilder.create();

  }

  @Override
  public OtherTraitsTemplate load(InputStream inputStream) {
    try {
      ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
      IOUtils.copy(inputStream, byteStream);
      String json = new String(byteStream.toByteArray());
      return gson.fromJson(json, OtherTraitsTemplate.class);
    } catch (IOException e) {
      throw new PersistenceException(e);
    }
  }
}
