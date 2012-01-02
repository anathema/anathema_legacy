package net.sf.anathema.character.equipment.impl.reporting.rendering.weaponry;

import net.sf.anathema.character.equipment.impl.reporting.content.AbstractWeaponryContent;
import net.sf.anathema.character.equipment.impl.reporting.content.Weaponry1stEditionContent;
import net.sf.anathema.character.equipment.impl.reporting.content.Weaponry2ndEditionContent;
import net.sf.anathema.character.generic.rules.IEditionVisitor;
import net.sf.anathema.character.generic.rules.IExaltedEdition;

public class WeaponryContentClassFinder implements IEditionVisitor {
  public Class<? extends AbstractWeaponryContent> contentClass;

  @Override
  public void visitFirstEdition(IExaltedEdition visitedEdition) {
    contentClass = Weaponry1stEditionContent.class;
  }

  @Override
  public void visitSecondEdition(IExaltedEdition visitedEdition) {
    contentClass = Weaponry2ndEditionContent.class;
  }
}
