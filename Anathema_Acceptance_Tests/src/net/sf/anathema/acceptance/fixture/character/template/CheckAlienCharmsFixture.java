package net.sf.anathema.acceptance.fixture.character.template;

import net.sf.anathema.character.abyssal.caste.AbyssalCaste;
import net.sf.anathema.character.db.aspect.DBAspect;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.type.AbstractSupportedCharacterTypeVisitor;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.lunar.caste.LunarCaste;
import net.sf.anathema.character.sidereal.caste.SiderealCaste;
import net.sf.anathema.character.solar.caste.SolarCaste;

public class CheckAlienCharmsFixture extends AbstractTemplateColumnFixture {

  public String caste;

  public boolean isAllowedAlienCharms() {
    return getTemplate().getMagicTemplate().getCharmTemplate().isAllowedAlienCharms(getNonEmptyCaste());
  }

  private ICasteType getNonEmptyCaste() {
    final ICasteType[] casteType = new ICasteType[1];
    getTemplate().getTemplateType().getCharacterType().accept(new AbstractSupportedCharacterTypeVisitor() {

      public void visitAbyssal(CharacterType visitedType) {
        casteType[0] = AbyssalCaste.valueOf(caste);
      }

      public void visitDB(CharacterType visitedType) {
        casteType[0] = DBAspect.valueOf(caste);
      }

      public void visitLunar(CharacterType type) {
        casteType[0] = LunarCaste.valueOf(caste);
      }

      public void visitSidereal(CharacterType visitedType) {
        casteType[0] = SiderealCaste.valueOf(caste);
      }

      public void visitMortal(CharacterType visitedType) {
        throw new IllegalArgumentException("Mortals have no caste"); //$NON-NLS-1$
      }

      public void visitSolar(CharacterType visitedType) {
        casteType[0] = SolarCaste.valueOf(caste);
      }
    });
    return casteType[0];
  }
}
