package net.sf.anathema.initialization;

import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.initialization.plugin.IAnathemaPluginManager;

import org.java.plugin.registry.Extension.Parameter;

public class AnathemaExtensionCollection extends AbstractInitializationCollection<IAnathemaExtension> {

  private static final String PARAM_ID = "id"; //$NON-NLS-1$
  private final Map<String, IAnathemaExtension> extensionsById = new LinkedHashMap<String, IAnathemaExtension>();

  public AnathemaExtensionCollection(IAnathemaPluginManager pluginManager) throws InitializationException {
    collectContent(pluginManager);
  }

  @Override
  protected void addItemForTypeParameter(Parameter typeParameter, IAnathemaExtension item) {
    Parameter subParameter = typeParameter.getSubParameter(PARAM_ID);
    String id = subParameter.valueAsString();
    extensionsById.put(id, item);
  }

  public Map<String, IAnathemaExtension> getExtensionsById() {
    return extensionsById;
  }

  @Override
  protected String getExtensionPointId() {
    return "AnathemaExtensions"; //$NON-NLS-1$
  }
}