package net.sf.anathema.character.generic.framework.magic.stringbuilder.type;

import java.text.MessageFormat;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.ICharmTypeStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicStringBuilderConstants;
import net.sf.anathema.character.generic.magic.charms.ICharmTypeVisitor;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.magic.charms.type.ICharmTypeModel;
import net.sf.anathema.character.generic.magic.charms.type.IReflexiveSpecialsModel;
import net.sf.anathema.character.generic.magic.charms.type.ISimpleSpecialsModel;
import net.sf.anathema.character.generic.magic.charms.type.TurnType;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractCharmTypeStringBuilder implements ICharmTypeStringBuilder {

  private final boolean displayDefaultValues;
  private final IResources resources;

  public AbstractCharmTypeStringBuilder(IResources resources, boolean displayDefaultValues) {
    this.resources = resources;
    this.displayDefaultValues = displayDefaultValues;
  }

  protected abstract StringBuilder buildDefenseString(
      ISimpleSpecialsModel model,
      boolean defaultSpeed,
      boolean longAction);

  private StringBuilder buildReflexiveModelString(IReflexiveSpecialsModel model) {
    StringBuilder builder = new StringBuilder();
    builder.append(IMagicStringBuilderConstants.Space);
    builder.append("("); //$NON-NLS-1$    
    MessageFormat formatter = new MessageFormat(""); //$NON-NLS-1$
    Object[] objects;
    if (model.getSecondaryStep() == null) {
      formatter.applyPattern(getResources().getString(getReflexiveSingleStepPattern()));
      objects = new Object[] { model.getPrimaryStep() };
    }
    else {
      formatter.applyPattern(getResources().getString(getReflexiveDualStepPattern()));
      objects = new Object[] { model.getPrimaryStep(), model.getSecondaryStep() };
    }
    builder.append(formatter.format(objects));
    builder.append(")"); //$NON-NLS-1$
    return builder;
  }

  private StringBuilder buildSimpleModelString(ISimpleSpecialsModel model) {
    StringBuilder builder = new StringBuilder();
    final boolean defaultSpeed = model.getSpeed() == ISimpleSpecialsModel.DEFAULT_SPEED;
    final boolean defaultDefense = model.getDefenseModifier() == ISimpleSpecialsModel.DEFAULT_DEFENSE_MODIFIER;
    final boolean defaultTurnType = model.getTurnType() == TurnType.Tick;
    if (defaultSpeed && defaultDefense && defaultTurnType) {
      return builder;
    }
    builder.append(IMagicStringBuilderConstants.Space);
    builder.append("("); //$NON-NLS-1$
    final boolean dramaticAction = model.getTurnType() == TurnType.DramaticAction;
    boolean longTick = model.getTurnType() == TurnType.LongTick;
    if (dramaticAction) {
      builder.append(getResources().getString(getDramaticActionKey()));
    }
    else {
      if (!defaultSpeed || longTick || displayDefaultValues) {
        builder.append(buildSpeedString(model));
      }
    }
    if (!defaultDefense || displayDefaultValues) {
      builder.append(buildDefenseString(model, defaultSpeed, dramaticAction||longTick));
    }
    builder.append(")"); //$NON-NLS-1$
    return builder;
  }

  protected abstract StringBuilder buildSpeedString(ISimpleSpecialsModel model);

  public String createTypeString(final ICharmTypeModel charmTypeModel) {
    final StringBuilder builder = new StringBuilder();
    final CharmType charmType = charmTypeModel.getCharmType();
    builder.append(getResources().getString(charmType.getId()));
    if (charmTypeModel.hasSpecialsModel()) {
      charmType.accept(new ICharmTypeVisitor() {
        public void visitExtraAction(CharmType visitedType) {
          // Nothing to do
        }

        public void visitPermanent(CharmType visitedType) {
          // Nothing to do
        }

        public void visitReflexive(CharmType visitedType) {
          builder.append(buildReflexiveModelString((IReflexiveSpecialsModel) charmTypeModel.getSpecialsModel()));
        }

        public void visitSimple(CharmType visitedType) {
          builder.append(buildSimpleModelString((ISimpleSpecialsModel) charmTypeModel.getSpecialsModel()));
        }

        public void visitSpecial(CharmType visitedType) {
          // Nothing to do
        }

        public void visitSupplemental(CharmType visitedType) {
          // Nothing to do
        }
      });
    }
    return builder.toString();
  }

  protected abstract String getDramaticActionKey();

  protected abstract String getReflexiveDualStepPattern();

  protected abstract String getReflexiveSingleStepPattern();

  protected IResources getResources() {
    return resources;
  }

}