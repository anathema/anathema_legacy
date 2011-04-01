package net.sf.anathema.acceptance.fixture.character.util;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.abyssal.caste.AbyssalCaste;
import net.sf.anathema.character.db.aspect.DBAspect;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.generic.type.AbstractSupportedCharacterTypeVisitor;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.lunar.caste.LunarCaste;
import net.sf.anathema.character.sidereal.caste.SiderealCaste;
import net.sf.anathema.character.solar.caste.SolarCaste;

public class CasteAcceptanceUtilties {

  public static ICasteType getNonEmptyCaste(ICharacterTemplate template, final String caste) {
    final List<ICasteType> casteType = new ArrayList<ICasteType>();
    template.getTemplateType().getCharacterType().accept(new AbstractSupportedCharacterTypeVisitor() {

      public void visitAbyssal(ICharacterType visitedType) {
        casteType.add(AbyssalCaste.valueOf(caste));
      }

      public void visitDB(ICharacterType visitedType) {
        casteType.add(DBAspect.valueOf(caste));
      }

      public void visitLunar(ICharacterType type) {
        casteType.add(LunarCaste.valueOf(caste));
      }

      public void visitSidereal(ICharacterType visitedType) {
        casteType.add(SiderealCaste.valueOf(caste));
      }

      public void visitMortal(ICharacterType visitedType) {
        throw new IllegalArgumentException("Mortals have no caste"); //$NON-NLS-1$
      }
      
      public void visitSpirit(ICharacterType visitedType) {
          throw new IllegalArgumentException("Spirits have no caste"); //$NON-NLS-1$
        }
      
      public void visitGhost(ICharacterType visitedType)
      {
    	  throw new IllegalArgumentException("Ghosts have no caste");
      }

      public void visitSolar(ICharacterType visitedType) {
        casteType.add(SolarCaste.valueOf(caste));
      }
    });
    return casteType.size() > 0 ? casteType.get(0) : null;
  }

}
