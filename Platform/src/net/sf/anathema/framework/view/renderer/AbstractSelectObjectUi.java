package net.sf.anathema.framework.view.renderer;

import javax.swing.Icon;

import net.disy.commons.swing.ui.IObjectUi;
import net.sf.anathema.lib.resources.IResources;

@SuppressWarnings("unchecked")
public abstract class AbstractSelectObjectUi<V> implements IObjectUi {

  private final IResources resources;

  public AbstractSelectObjectUi(IResources resources) {
    this.resources = resources;
  }

  public Icon getIcon(Object value) {
    if (value == null) {
      return null;
    }
    return getNonNullIcon((V) value);
  }

  protected abstract Icon getNonNullIcon(V value);

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