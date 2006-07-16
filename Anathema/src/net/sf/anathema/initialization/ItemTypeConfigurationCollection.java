package net.sf.anathema.initialization;

import java.util.ArrayList;
import java.util.Collection;

import net.sf.anathema.framework.InitializationException;
import net.sf.anathema.framework.module.AbstractItemTypeConfiguration;
import net.sf.anathema.initialization.plugin.IAnathemaPluginManager;

import org.java.plugin.registry.Extension.Parameter;

public class ItemTypeConfigurationCollection extends AbstractInitializationCollection<AbstractItemTypeConfiguration> {

  private static final String EXTENSION_POINT_ITEM_TYPES = "ItemTypes"; //$NON-NLS-1$
  private static final String PARAM_DEVELOPMENT = "development"; //$NON-NLS-1$
  private final Collection<AbstractItemTypeConfiguration> itemTypeConfigurations = new ArrayList<AbstractItemTypeConfiguration>();
  private final boolean isDevelopment;

  public ItemTypeConfigurationCollection(IAnathemaPluginManager pluginManager, boolean isDevelopment)
      throws InitializationException {
    this.isDevelopment = isDevelopment;
    collectContent(pluginManager);
  }

  @Override
  protected String getExtensionPointId() {
    return EXTENSION_POINT_ITEM_TYPES;
  }

  public Collection<AbstractItemTypeConfiguration> getItemTypes() {
    return new ArrayList<AbstractItemTypeConfiguration>(itemTypeConfigurations);
  }

  @Override
  protected void addItemForTypeParameter(Parameter typeParameter, AbstractItemTypeConfiguration itemType) {
    Parameter subParameter = typeParameter.getSubParameter(PARAM_DEVELOPMENT);
    boolean developmentOnly = subParameter != null && subParameter.valueAsBoolean();
    if (!isDevelopment && developmentOnly) {
      return;
    }
    System.out.println("Adding: "+ itemType.getClass().getCanonicalName());
    itemTypeConfigurations.add(itemType);
  }
}