package net.sf.anathema.character.generic.framework.xml.registry;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.ICharacterTemplateExtensionResourceCache;
import net.sf.anathema.character.generic.framework.xml.ITemplateParser;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.registry.Registry;
import net.sf.anathema.lib.resources.ResourceFile;
import net.sf.anathema.lib.xml.DocumentUtilities;

import org.dom4j.Document;

import com.google.common.base.Preconditions;

public class XmlTemplateRegistry<T> implements IXmlTemplateRegistry<T> {

  private final List<String> idsInProgress = new ArrayList<String>();
  private final IRegistry<String, T> templateRegistry = new Registry<String, T>();
  private final ICharacterTemplateExtensionResourceCache cache;
  private ITemplateParser<T> templateParser;
  
  public XmlTemplateRegistry(ICharacterTemplateExtensionResourceCache cache) {
	  this.cache = cache;
  }

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
	  // First try to find a canon source file
	  ResourceFile resource = cache.getTemplateResource(prefix + "data/" + id);
	  // That failing, attempt to find a custom source file
	  if (resource == null) {
		  resource = cache.getTemplateResource(id);
	  }
	  // If a source file has been found, load that.
	  if (resource != null) {
		  return get(resource);
	  }
	  // If all of that fails, try to directly load non-dataset files.
	  // (At this time, this method is only necessary for God-Blooded,
	  //  and may be safely removed if we clean house for Ex3)
	  return get(id, new IDocumentOpener() {
		  @Override
		  public Document openDocument() throws Exception {
			  InputStream resourceAsStream = XmlTemplateRegistry.class.getClassLoader().getResourceAsStream(prefix + "data/" + id); //$NON-NLS-1$
			  if (resourceAsStream == null) {
				  resourceAsStream = XmlTemplateRegistry.class.getClassLoader().getResourceAsStream(id);
			  }
			  return DocumentUtilities.read(resourceAsStream);
		  }
	  });
  }
  
  @Override
  public T get(final ResourceFile resource) throws PersistenceException {
	return get(resource.getFileName(), new IDocumentOpener() {
		@Override
		public Document openDocument() throws Exception {
		    return DocumentUtilities.read(resource.getURL().openStream());
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