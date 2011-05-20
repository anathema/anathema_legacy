package net.sf.anathema.character.mutations.template;

import net.sf.anathema.character.generic.rules.IEditionVisitor;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.lib.util.Identificate;

public class MutationsTemplate extends Identificate implements IGlobalAdditionalTemplate {

  public static final String ID = "Mutations"; //$NON-NLS-1$

  public MutationsTemplate() {
    super(ID);
  }

  public int getMaximalBonusPointGain() {
    return 10;
  }

  public boolean supportsEdition(IExaltedEdition edition) {
    final boolean[] supported = new boolean[1];
    edition.accept(new IEditionVisitor() {
      public void visitFirstEdition(IExaltedEdition visitedEdition) {
        supported[0] = false;
      }

      public void visitSecondEdition(IExaltedEdition visitedEdition) {
        supported[0] = true;
      }
    });
    return supported[0];
  }
}