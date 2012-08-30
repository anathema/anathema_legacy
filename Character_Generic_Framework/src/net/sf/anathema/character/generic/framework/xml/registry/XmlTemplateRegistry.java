package net.sf.anathema.character.generic.framework.xml.registry;

import com.google.common.base.Preconditions;
import net.sf.anathema.character.generic.framework.xml.ITemplateParser;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.registry.Registry;
import net.sf.anathema.lib.resources.ResourceFile;
import net.sf.anathema.lib.xml.DocumentUtilities;
import org.dom4j.Document;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlTemplateRegistry<T> implements IXmlTemplateRegistry<T> {

  private final List<String> idsInProgress = new ArrayList<String>();
  private final IRegistry<String, T> templateRegistry = new Registry<String, T>();
  private ITemplateParser<T> templateParser;

  @Override
  public void setTemplateParser(ITemplateParser<T> templateParser) {
    this.templateParser = templateParser;
  }

  @Override
  public T get(String id) throws PersistenceException {
    return get(id, "");
  }
  
  @Override
  public T get(String id, String prefix) throws PersistenceException {
    T template = templateRegistry.get(id);
    if (template != null) {
      return template;
    }
    Preconditions.checkNotNull(templateParser);
    if (idsInProgress.contains(id)) {
      throw new PersistenceException("Illegal recursion in template file:" + id); //$NON-NLS-1$
    }
    idsInProgress.add(id);
    InputStream resourceAsStream = XmlTemplateRegistry.class.getClassLoader().getResourceAsStream(prefix + "data/" + id); //$NON-NLS-1$
    Document document;
    document = DocumentUtilities.read(resourceAsStream);
    T parsedTemplate = templateParser.parseTemplate(document.getRootElement());
    templateRegistry.register(id, parsedTemplate);
    idsInProgress.remove(id);
    return parsedTemplate;
  }
  
  @Override
  public T get(ResourceFile resource) throws PersistenceException {
    T template = templateRegistry.get(resource.getFileName());
    if (template != null) {
      return template;
    }
    Preconditions.checkNotNull(templateParser);
    if (idsInProgress.contains(resource.getFileName())) {
      throw new PersistenceException("Illegal recursion in template file:" + resource.getFileName()); //$NON-NLS-1$
    }
    idsInProgress.add(resource.getFileName());
    Document document;
	try {
		document = DocumentUtilities.read(resource.getURL());
	} catch (Exception e) {
		throw new PersistenceException("Unable to find file " + resource.getURL());
	}
    T parsedTemplate = templateParser.parseTemplate(document.getRootElement());
    templateRegistry.register(resource.getFileName(), parsedTemplate);
    idsInProgress.remove(resource.getFileName());
    return parsedTemplate;
  }
}