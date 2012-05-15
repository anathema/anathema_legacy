package net.sf.anathema.framework.view.renderer;

import net.sf.anathema.lib.gui.ui.IObjectUi;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Icon;

@SuppressWarnings("unchecked")
public abstract class AbstractSelectObjectUi<V> implements IObjectUi<Object> {

  private final IResources resources;

  public AbstractSelectObjectUi(IResources resources) {
    this.resources = resources;
  }
  
  @Override
  public String getToolTipText(Object value) {
    return null;
  }

  @Override
  public Icon getIcon(Object value) {
    if (value == null) {
      return null;
    }
    return getNonNullIcon((V) value);
  }

  protected abstract Icon getNonNullIcon(V value);

  @Override
  public String getLabel(Object value) {
    if (value == null) {
      return resources.getString("ComboBox.SelectLabel"); //$NON-NLS-1$
    }
    return getNonNullLabel((V) value);
  }

  protected abstract String getNonNullLabel(V value);

  protected final IResources getResources() {
    return resources;
  }
}