package net.sf.anathema.character.lunar.beastform.model.gift;

import net.sf.anathema.character.mutations.model.IMutation;
import net.sf.anathema.character.mutations.model.IMutationRules;

public class DBTMutationRules implements IMutationRules {

  private final static String LARGE_MUTATION = "Large";

  @Override
  public boolean acceptMutation(IMutation mutation) {
    return !mutation.getId().equals(LARGE_MUTATION) && mutation.getCost() >= 0;
  }
}