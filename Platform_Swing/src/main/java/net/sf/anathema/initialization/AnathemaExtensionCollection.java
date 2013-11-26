package net.sf.anathema.initialization;

import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.extension.IAnathemaExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class AnathemaExtensionCollection implements Iterable<ExtensionWithId> {

  private final List<ExtensionWithId> extensions = new ArrayList<>();

  public AnathemaExtensionCollection(ObjectFactory objectFactory) throws InitializationException {
    for (IAnathemaExtension extension : objectFactory.instantiateAllImplementers(IAnathemaExtension.class)) {
      String id = extension.getClass().getAnnotation(Id.class).value();
      extensions.add(new ExtensionWithId(id, extension));
    }
  }

  @Override
  public Iterator<ExtensionWithId> iterator() {
    return extensions.iterator();
  }
}