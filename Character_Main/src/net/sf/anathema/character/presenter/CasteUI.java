package net.sf.anathema.character.presenter;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

public class CasteUI extends AbstractUI {

  private final IExaltedEdition edition;
  private final IPresentationProperties properties;

  public CasteUI(IResources resources, IExaltedEdition edition, IPresentationProperties properties) {
    super(resources);
    this.edition = edition;
    this.properties = properties;
  }

  public Icon getSmallCasteIcon(ICasteType< ? > type) {
    return getIcon(properties.getSmallCasteIconResource(type.getId(), edition.getId()));
  }

  public Icon getEmptyIcon() {
    return new Icon() {

      public void paintIcon(Component c, Graphics g, int x, int y) {
        // Nothing to do
      }

      public int getIconWidth() {
        return 16;
      }

      public int getIconHeight() {
        return 16;
      }
    };
  }
}