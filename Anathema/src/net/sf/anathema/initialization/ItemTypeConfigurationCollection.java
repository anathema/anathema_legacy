package net.sf.anathema.initialization;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.anathema.framework.InitializationException;
import net.sf.anathema.framework.module.AbstractItemTypeConfiguration;
import net.sf.anathema.initialization.plugin.AnathemaPluginManager;
import net.sf.anathema.initialization.plugin.IAnathemaPluginManager;
import net.sf.anathema.initialization.plugin.IPluginConstants;

import org.java.plugin.registry.Extension;
import org.java.plugin.registry.Extension.Parameter;

public class ItemTypeConfigurationCollection {

  private static final String EXTENSION_POINT_ITEM_TYPES = "ItemTypes"; //$NON-NLS-1$
  private static final String PARAM_CLASS = "class"; //$NON-NLS-1$
  private static final String PARAM_DEVELOPMENT = "development"; //$NON-NLS-1$
  private static final String PARAM_TYPE = "type"; //$NON-NLS-1$
  private final Collection<AbstractItemTypeConfiguration> itemTypeConfigurations = new ArrayList<AbstractItemTypeConfiguration>();
  private final boolean isDevelopment;

  public ItemTypeConfigurationCollection(IAnathemaPluginManager pluginManager, boolean isDevelopment)
      throws InitializationException {
    this.isDevelopment = isDevelopment;
    collectItemTypes(pluginManager);
  }

  private void collectItemTypes(IAnathemaPluginManager pluginManager) throws InitializationException {
    for (Extension extension : pluginManager.getExtension(IPluginConstants.PLUGIN_CORE, EXTENSION_POINT_ITEM_TYPES)) {
      for (Parameter typeParameter : AnathemaPluginManager.getParameters(extension, PARAM_TYPE)) {
        AbstractItemTypeConfiguration itemType = createItemType(typeParameter);
        if (itemType != null) {
          itemTypeConfigurations.add(itemType);
        }
      }
    }
  }

  public Collection<AbstractItemTypeConfiguration> getItemTypes() {
    return new ArrayList<AbstractItemTypeConfiguration>(itemTypeConfigurations);
  }

  private AbstractItemTypeConfiguration createItemType(Parameter typeParameter) throws InitializationException {
    boolean developmentOnly = typeParameter.getSubParameter(PARAM_DEVELOPMENT).valueAsBoolean();
    if (!isDevelopment && developmentOnly) {
      return null;
    }
    return instantiateItemType(typeParameter.getSubParameter(PARAM_CLASS));
  }

  private AbstractItemTypeConfiguration instantiateItemType(Parameter classParameter) throws InitializationException {
    String className = classParameter.valueAsString();
    try {
      return (AbstractItemTypeConfiguration) Class.forName(className).newInstance();
    }
    catch (Throwable throwable) {
      throw new InitializationException(throwable);
    }
  }
}