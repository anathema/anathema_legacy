package net.sf.anathema.character.db.template;

import java.awt.Color;

import net.sf.anathema.character.db.aspect.DBAspect;
import net.sf.anathema.character.db.aspect.IDBAspectVisitor;
import net.sf.anathema.character.generic.impl.IIconConstants;
import net.sf.anathema.character.generic.impl.template.presentation.AbstractPresentationProperties;
import net.sf.anathema.character.generic.template.ITemplateType;
import net.sf.anathema.character.generic.template.presentation.ICharmPresentationProperties;

public final class DbPresentationProperties extends AbstractPresentationProperties {

  private final ICharmPresentationProperties charmPresentationProperties = new DbCharmPresentationProperties();

  public DbPresentationProperties(ITemplateType templateType) {
    super(templateType);
  }

  public String getBallResource() {
    return IIconConstants.DB_BALL;
  }

  public String getSmallCasteIconResource(String casteId) {
    final String[] iconName = new String[1];
    DBAspect dbAspect = DBAspect.valueOf(casteId);
    dbAspect.accept(new IDBAspectVisitor() {
      public void visitAir(DBAspect aspect) {
        iconName[0] = IIconConstants.DB_AIRASPECT_SMALL;
      }

      public void visisEarth(DBAspect aspect) {
        iconName[0] = IIconConstants.DB_EARTHASPECT_SMALL;
      }

      public void visitFire(DBAspect aspect) {
        iconName[0] = IIconConstants.DB_FIREASPECT_SMALL;
      }

      public void visitWater(DBAspect aspect) {
        iconName[0] = IIconConstants.DB_WATERASPECT_SMALL;
      }

      public void visitWood(DBAspect aspect) {
        iconName[0] = IIconConstants.DB_WOODASPECT_SMALL;
      }
    });
    return iconName[0];
  }

  public String getMediumCasteIconResource(String groupId) {
    final String[] iconName = new String[1];
    DBAspect dbAspect = DBAspect.valueOf(groupId);
    dbAspect.accept(new IDBAspectVisitor() {
      public void visitAir(DBAspect aspect) {
        iconName[0] = IIconConstants.DB_AIRASPECT;
      }

      public void visisEarth(DBAspect aspect) {
        iconName[0] = IIconConstants.DB_EARTHASPECT;
      }

      public void visitFire(DBAspect aspect) {
        iconName[0] = IIconConstants.DB_FIREASPECT;
      }

      public void visitWater(DBAspect aspect) {
        iconName[0] = IIconConstants.DB_WATERASPECT;
      }

      public void visitWood(DBAspect aspect) {
        iconName[0] = IIconConstants.DB_WOODASPECT;
      }
    });
    return iconName[0];
  }

  public Color getColor() {
    return new Color(139, 0, 0);
  }

  @Override
  public String getCasteLabelResource() {
    return getCharacterTypeId() + ".Caste.Label"; //$NON-NLS-1$;
  }

  @Override
  public String getNewActionResource() {
    return "CharacterGenerator.Templates.Dragon-Blooded." + getTemplateType().getSubType().getId(); //$NON-NLS-1$
  }

  public ICharmPresentationProperties getCharmPresentationProperties() {
    return charmPresentationProperties;
  }
}