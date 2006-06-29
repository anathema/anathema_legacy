package net.sf.anathema.initialization;

import net.sf.anathema.framework.InitializationException;
import net.sf.anathema.initialization.plugin.IAnathemaPluginManager;
import net.sf.anathema.initialization.plugin.IPluginConstants;
import net.sf.anathema.initialization.plugin.PluginUtilities;

import org.java.plugin.registry.Extension;
import org.java.plugin.registry.Extension.Parameter;

public abstract class AbstractInitializationCollection<T> {

  private static final String PARAM_CLASS = "class"; //$NON-NLS-1$
  private static final String PARAM_TYPE = "type"; //$NON-NLS-1$

  protected final void collectContent(IAnathemaPluginManager pluginManager) throws InitializationException {
    for (Extension extension : pluginManager.getExtension(IPluginConstants.PLUGIN_CORE, getExtensionPointId())) {
      for (Parameter typeParameter : PluginUtilities.getParameters(extension, PARAM_TYPE)) {
        T itemType = createItemType(typeParameter);
        addItemForTypeParameter(typeParameter, itemType);
      }
    }
  }

  protected abstract void addItemForTypeParameter(Parameter typeParameter, T item);

  protected abstract String getExtensionPointId();

  private T createItemType(Parameter typeParameter) throws InitializationException {
    return instantiateItemType(typeParameter.getSubParameter(PARAM_CLASS));
  }

  @SuppressWarnings("unchecked")
  private final T instantiateItemType(Parameter classParameter) throws InitializationException {
    String className = classParameter.valueAsString();
    try {
      return (T) Class.forName(className).newInstance();
    }
    catch (Throwable throwable) {
      throw new InitializationException(throwable);
    }
  }
}