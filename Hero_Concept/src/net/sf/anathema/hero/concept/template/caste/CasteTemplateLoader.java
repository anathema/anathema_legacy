package net.sf.anathema.hero.concept.template.caste;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.anathema.hero.concept.CasteTemplate;
import net.sf.anathema.hero.template.TemplateFactory;
import net.sf.anathema.hero.template.TemplateLoader;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.util.SimpleIdentifier;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CasteTemplateLoader implements TemplateLoader<CasteTemplate> {

  public static CasteTemplate loadTemplate(TemplateFactory templateFactory, String templateName) {
    SimpleIdentifier templateId = new SimpleIdentifier(templateName);
    return templateFactory.loadModelTemplate(templateId, new CasteTemplateLoader());
  }

  private final Gson gson;

  public CasteTemplateLoader() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setPrettyPrinting();
    gson = gsonBuilder.create();

  }

  @Override
  public CasteTemplate load(InputStream inputStream) {
    try {
      ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
      IOUtils.copy(inputStream, byteStream);
      String json = new String(byteStream.toByteArray());
      return gson.fromJson(json, CasteTemplate.class);
    } catch (IOException e) {
      throw new PersistenceException(e);
    }
  }
}