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
  public T get(final String id, final String prefix) throws PersistenceException {
	return get(id, new IDocumentOpener() {
		@Override
		public Document openDocument() throws Exception {
			InputStream resourceAsStream = XmlTemplateRegistry.class.getClassLoader().getResourceAsStream(prefix + "data/" + id); //$NON-NLS-1$
		    return DocumentUtilities.read(resourceAsStream);
		}
	});
  }
  
  @Override
  public T get(final ResourceFile resource) throws PersistenceException {
	return get(resource.getFileName(), new IDocumentOpener() {
		@Override
		public Document openDocument() throws Exception {
			return DocumentUtilities.read(resource.getURL());
		}
	});
  }
  
  private T get(String id, IDocumentOpener opener) throws PersistenceException {
    T template = templateRegistry.get(id);
    if (template != null) {
      return template;
    }
    Preconditions.checkNotNull(templateParser);
    if (idsInProgress.contains(id)) {
      throw new PersistenceException("Illegal recursion in template file:" + id); //$NON-NLS-1$
    }
    idsInProgress.add(id);
    Document document;
	try {
		document = opener.openDocument();
	} catch (Exception e) {
		throw new PersistenceException("Unable to find file " + id);
	}
    T parsedTemplate = templateParser.parseTemplate(document.getRootElement());
    templateRegistry.register(id, parsedTemplate);
    idsInProgress.remove(id);
    return parsedTemplate;
  }
  
  private interface IDocumentOpener {
	  Document openDocument() throws Exception;
  }
}