package net.sf.anathema.acceptance.fixture.character.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.abyssal.caste.AbyssalCaste;
import net.sf.anathema.character.db.aspect.DBAspect;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.caste.ICasteTypeVisitor;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.type.AbstractSupportedCharacterTypeVisitor;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.lunar.caste.LunarCaste;
import net.sf.anathema.character.sidereal.caste.SiderealCaste;
import net.sf.anathema.character.solar.caste.SolarCaste;

public class CasteAcceptanceUtilties {

  public static ICasteType< ? extends ICasteTypeVisitor> getNonEmptyCaste(
      ICharacterTemplate template,
      final String caste) {
    final List<ICasteType< ? extends ICasteTypeVisitor>> casteType = new ArrayList<ICasteType< ? extends ICasteTypeVisitor>>();
    template.getTemplateType().getCharacterType().accept(new AbstractSupportedCharacterTypeVisitor() {

      public void visitAbyssal(CharacterType visitedType) {
        casteType.add(AbyssalCaste.valueOf(caste));
      }

      public void visitDB(CharacterType visitedType) {
        casteType.add(DBAspect.valueOf(caste));
      }

      public void visitLunar(CharacterType type) {
        casteType.add(LunarCaste.valueOf(caste));
      }

      public void visitSidereal(CharacterType visitedType) {
        casteType.add(SiderealCaste.valueOf(caste));
      }

      public void visitMortal(CharacterType visitedType) {
        throw new IllegalArgumentException("Mortals have no caste"); //$NON-NLS-1$
      }

      public void visitSolar(CharacterType visitedType) {
        casteType.add(SolarCaste.valueOf(caste));
      }
    });
    return casteType.size() > 0 ? casteType.get(0) : null;
  }

}
