package net.sf.anathema.character.generic.framework.xml.registry;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.disy.commons.core.util.Ensure;
import net.sf.anathema.character.generic.framework.xml.ITemplateParser;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.registry.Registry;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;

public class XmlTemplateRegistry<T> implements IXmlTemplateRegistry<T> {

  private final List<String> idsInProgress = new ArrayList<String>();
  private final IRegistry<String, T> templateRegistry = new Registry<String, T>();
  private ITemplateParser<T> templateParser;

  public void setTemplateParser(ITemplateParser<T> templateParser) {
    this.templateParser = templateParser;
  }

  public T get(String id) throws PersistenceException {
    T template = templateRegistry.get(id);
    if (template != null) {
      return template;
    }
    Ensure.ensureNotNull(templateParser);
    if (idsInProgress.contains(id)) {
      throw new PersistenceException("Illegal recursion in template file:" + id); //$NON-NLS-1$
    }
    idsInProgress.add(id);
    InputStream resourceAsStream = XmlTemplateRegistry.class.getClassLoader().getResourceAsStream("data/" + id); //$NON-NLS-1$
    Document document;
    document = DocumentUtilities.read(resourceAsStream);
    T parsedTemplate = templateParser.parseTemplate(document.getRootElement());
    templateRegistry.register(id, parsedTemplate);
    idsInProgress.remove(id);
    return parsedTemplate;
  }
}