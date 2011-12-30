package net.sf.anathema.character.meritsflaws.template;

import net.sf.anathema.character.generic.rules.IEditionVisitor;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.generic.template.additional.IGlobalAdditionalTemplate;
import net.sf.anathema.lib.util.Identificate;

public class MeritsFlawsTemplate extends Identificate implements IGlobalAdditionalTemplate {

  public static final String ID = "MeritsAndFlaws"; //$NON-NLS-1$

  public MeritsFlawsTemplate() {
    super(ID);
  }

  public boolean supportsEdition(IExaltedEdition edition) {
    final boolean[] supported = new boolean[1];
    edition.accept(new IEditionVisitor() {
      public void visitFirstEdition(IExaltedEdition visitedEdition) {
        supported[0] = true;
      }

      public void visitSecondEdition(IExaltedEdition visitedEdition) {
        supported[0] = false;
      }
    });
    return supported[0];
  }
}