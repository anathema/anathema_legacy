package net.sf.anathema.initialization;

import net.sf.anathema.framework.environment.ObjectFactory;
import net.sf.anathema.framework.environment.ResourceLoader;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.initialization.repository.DataFileProvider;

@Id(AnathemaExtensionCollectionTest.DUMMYID)
public class DummyExtension implements IAnathemaExtension {
  @Override
  public void initialize(DataFileProvider dataFileProvider, ObjectFactory instantiater, ResourceLoader loader) {
    //nothing to do
  }
}
