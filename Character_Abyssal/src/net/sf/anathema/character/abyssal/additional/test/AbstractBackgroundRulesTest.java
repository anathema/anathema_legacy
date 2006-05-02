package net.sf.anathema.character.abyssal.additional.test;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.impl.backgrounds.SimpleBackgroundTemplate;
import net.sf.anathema.character.generic.impl.magic.Spell;
import net.sf.anathema.character.generic.impl.magic.test.DummyCharm;
import net.sf.anathema.character.generic.impl.testing.DummyGenericTrait;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.ISpell;
import net.sf.anathema.character.generic.magic.spells.CircleType;
import net.sf.anathema.lib.testing.BasicTestCase;

import org.easymock.MockControl;
import org.easymock.internal.MocksControl;

public abstract class AbstractBackgroundRulesTest extends BasicTestCase {

  private IBackgroundTemplate backgroundTemplate;
  private IGenericCharacter traitCollection;
  private MockControl traitCollectionControl;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    traitCollectionControl = MockControl.createNiceControl(IGenericCharacter.class);
    traitCollection = (IGenericCharacter) traitCollectionControl.getMock();
    backgroundTemplate = new SimpleBackgroundTemplate("Test"); //$NON-NLS-1$
  }

  protected final void initTraitCollectionControlForValue(int value) {
    DummyGenericTrait dummyTrait = new DummyGenericTrait(backgroundTemplate, value);
    traitCollection.getTrait(backgroundTemplate);
    traitCollectionControl.setReturnValue(dummyTrait, MocksControl.AT_LEAST_ONCE);
    traitCollectionControl.replay();
  }

  protected final IGenericCharacter getTraitCollection() {
    return traitCollection;
  }

  protected final IBackgroundTemplate getBackgroundTemplate() {
    return backgroundTemplate;
  }

  protected final ISpell createSpell(CircleType type) {
    return new Spell(null, type, null, null, null);
  }

  protected final ICharm createCharm() {
    return new DummyCharm("id"); //$NON-NLS-1$
  }
}