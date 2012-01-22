package net.sf.anathema.initialization;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.anathema.framework.extension.IAnathemaExtension;

public class AnathemaExtensionCollection {

  private final Map<String, IAnathemaExtension> extensionsById = new LinkedHashMap<String, IAnathemaExtension>();

  public AnathemaExtensionCollection(Instantiater instantiater) throws InitializationException {
    Collection<IAnathemaExtension> extensions = instantiater.instantiateAll(Extension.class);
    for (IAnathemaExtension extension : extensions) {
      Extension annotation = extension.getClass().getAnnotation(Extension.class);
      String id = annotation.id();
      extensionsById.put(id, extension);
    }
  }

  public Map<String, IAnathemaExtension> getExtensionsById() {
    return extensionsById;
  }
}