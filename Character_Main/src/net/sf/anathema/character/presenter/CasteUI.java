package net.sf.anathema.character.presenter;

import net.disy.commons.swing.icon.EmptyIcon;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.impl.persistence.SecondEdition;
import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

import javax.swing.Icon;
import java.awt.Dimension;

public class CasteUI extends AbstractUI {

  private final IPresentationProperties properties;

  public CasteUI(IResources resources, IPresentationProperties properties) {
    super(resources);
    this.properties = properties;
  }

  public Icon getSmallCasteIcon(ICasteType type) {
    return getIcon(properties.getSmallCasteIconResource(type.getId(), SecondEdition.SECOND_EDITION));
  }

  public Icon getEmptyIcon() {
    return new EmptyIcon(new Dimension(16, 16));
  }
}