package net.sf.anathema.character.generic.framework.resources;

import javax.swing.Icon;

import net.sf.anathema.character.generic.impl.IIconConstants;
import net.sf.anathema.character.generic.type.AbstractSupportedCharacterTypeVisitor;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.lib.resources.AbstractUI;
import net.sf.anathema.lib.resources.IResources;

public class CharacterUI extends AbstractUI {

  public CharacterUI(IResources resources) {
    super(resources);
  }

  public Icon getCharacterDescriptionTabIcon() {
    return getIcon("CharacterTabIcon.png"); //$NON-NLS-1$
  }

  public Icon getMediumLockIcon() {
    return getIcon("Lock20.png"); //$NON-NLS-1$
  }

  public Icon getSmallCharacterTypeIcon(CharacterType characterType) {
    final Icon[] typeIcon = new Icon[1];
    characterType.accept(new AbstractSupportedCharacterTypeVisitor() {
      public void visitSolar(CharacterType visitedType) {
        typeIcon[0] = getIcon(IIconConstants.SOLAR_ICON_SMALL);
      }

      public void visitMortal(CharacterType visitedType) {
        typeIcon[0] = getIcon(IIconConstants.MORTAL_ICON_SMALL);
      }

      public void visitLunar(CharacterType type) {
        typeIcon[0] = getIcon(IIconConstants.LUNAR_ICON_SMALL);
      }

      public void visitSidereal(CharacterType visitedType) {
        typeIcon[0] = getIcon(IIconConstants.SIDEREAL_ICON_SMALL);
      }

      public void visitDB(CharacterType visitedType) {
        typeIcon[0] = getIcon(IIconConstants.DB_ICON_SMALL);
      }

      public void visitAbyssal(CharacterType visitedType) {
        typeIcon[0] = getIcon(IIconConstants.ABYSSAL_ICON_SMALL);
      }
    });
    return typeIcon[0];
  }

  public Icon getTinyCharacterTypeIcon(CharacterType characterType) {
    final Icon[] typeIcon = new Icon[1];
    characterType.accept(new AbstractSupportedCharacterTypeVisitor() {
      public void visitSolar(CharacterType visitedType) {
        typeIcon[0] = getIcon(IIconConstants.SOLAR_ICON_TINY);
      }

      public void visitMortal(CharacterType visitedType) {
        typeIcon[0] = getIcon(IIconConstants.MORTAL_ICON_TINY);

      }

      public void visitSidereal(CharacterType visitedType) {
        typeIcon[0] = getIcon(IIconConstants.SIDEREAL_ICON_TINY);

      }

      public void visitDB(CharacterType visitedType) {
        typeIcon[0] = getIcon(IIconConstants.DB_ICON_TINY);
      }

      public void visitAbyssal(CharacterType visitedType) {
        typeIcon[0] = getIcon(IIconConstants.ABYSSAL_ICON_TINY);
      }

      public void visitLunar(CharacterType type) {
        typeIcon[0] = getIcon(IIconConstants.LUNAR_ICON_TINY);
      }
    });
    return typeIcon[0];
  }

  public Icon getNewCharacterToolBarIcon() {
    return getIcon("toolbar/TaskBarNew24.png"); //$NON-NLS-1$
  }

  public Icon getLoadCharacterToolBarIcon() {
    return getIcon("toolbar/TaskBarOpen24.png"); //$NON-NLS-1$
  }

  public Icon getRandomRealmNameIcon() {
    return getIcon("util/question-mark-purple.gif"); //$NON-NLS-1$
  }

  public Icon getRandomThresholdNameIcon() {
    return getIcon("util/question-mark.gif"); //$NON-NLS-1$
  }

  public Icon getEditComboIcon() {
    return getIcon("Recycle20.png"); //$NON-NLS-1$
  }

  public Icon getCancelComboEditIcon() {
    return getIcon("CancelEdit20.png"); //$NON-NLS-1$
  }
}