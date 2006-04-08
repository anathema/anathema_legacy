package net.sf.anathema.framework.repository.filechooser;

import net.disy.commons.core.message.BasicMessage;
import net.disy.commons.core.message.IBasicMessage;
import net.disy.commons.swing.message.MessageType;
import net.sf.anathema.framework.extension.IExtensionPoint;
import net.sf.anathema.framework.item.IItemType;
import net.sf.anathema.framework.repository.AbstractObjectSelectionProperties;
import net.sf.anathema.framework.repository.IObjectSelectionProperties;
import net.sf.anathema.lib.registry.Registry;
import net.sf.anathema.lib.resources.IResources;

public class RepositoryFileChooserPropertiesExtensionPoint extends Registry<IItemType, IObjectSelectionProperties> implements
    IExtensionPoint {

  public static final String ID = RepositoryFileChooserPropertiesExtensionPoint.class.getName();

  public RepositoryFileChooserPropertiesExtensionPoint(IResources resources) {
    super(new AbstractObjectSelectionProperties(resources) {
      public IBasicMessage getDefaultMessage() {
        return new BasicMessage(
            getResources().getString("AnathemaPersistence.LoadAction.Message.Default"), MessageType.NORMAL); //$NON-NLS-1$
      }

      public String getTitle() {
        return getResources().getString("AnathemaPersistence.LoadAction.Title"); //$NON-NLS-1$
      }
    });
  }
}