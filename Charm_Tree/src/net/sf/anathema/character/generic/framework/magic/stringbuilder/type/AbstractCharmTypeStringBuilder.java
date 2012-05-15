package net.sf.anathema.character.generic.framework.magic.stringbuilder.type;

import net.sf.anathema.character.generic.framework.magic.stringbuilder.ICharmTypeStringBuilder;
import net.sf.anathema.character.generic.framework.magic.stringbuilder.IMagicTooltipStringBuilder;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.magic.charms.ICharmTypeVisitor;
import net.sf.anathema.character.generic.magic.charms.type.CharmType;
import net.sf.anathema.character.generic.magic.charms.type.ICharmTypeModel;
import net.sf.anathema.character.generic.magic.charms.type.IReflexiveSpecialsModel;
import net.sf.anathema.character.generic.magic.charms.type.ISimpleSpecialsModel;
import net.sf.anathema.character.generic.magic.charms.type.TurnType;
import net.sf.anathema.lib.resources.IResources;

import java.text.MessageFormat;

public abstract class AbstractCharmTypeStringBuilder implements ICharmTypeStringBuilder, IMagicTooltipStringBuilder {

  private final boolean displayDefaultValues;
  private final IResources resources;

  public AbstractCharmTypeStringBuilder(IResources resources, boolean displayDefaultValues) {
    this.resources = resources;
    this.displayDefaultValues = displayDefaultValues;
  }

  @Override
  public void buildStringForMagic(StringBuilder builder, IMagic magic, Object details) {
    if (magic instanceof ICharm) {
      builder.append(resources.getString("CharmTreeView.ToolTip.Type")); //$NON-NLS-1$
      builder.append(ColonSpace);
      builder.append(createTypeString(((ICharm) magic).getCharmTypeModel()));
      builder.append(HtmlLineBreak);
    }
  }

  protected abstract StringBuilder buildDefenseString(ISimpleSpecialsModel model, boolean defaultSpeed,
          boolean longAction);

  private StringBuilder buildReflexiveModelString(IReflexiveSpecialsModel model) {
    StringBuilder builder = new StringBuilder();
    builder.append(IMagicTooltipStringBuilder.Space);
    builder.append("("); //$NON-NLS-1$    
    MessageFormat formatter = new MessageFormat(""); //$NON-NLS-1$
    Object[] objects;
    if (model.getSecondaryStep() == null) {
      formatter.applyPattern(getResources().getString(getReflexiveSingleStepPattern()));
      if (model.getPrimaryStep() == null) {
        objects = new Object[]{-1};
      } else {
        objects = new Object[]{model.getPrimaryStep()};
      }
    } else {
      formatter.applyPattern(getResources().getString(getReflexiveDualStepPattern()));
      if (model.getPrimaryStep() == null) {
        objects = new Object[]{-1, model.getSecondaryStep()};
      } else {
        objects = new Object[]{model.getPrimaryStep(), model.getSecondaryStep()};
      }
    }
    builder.append(formatter.format(objects));
    builder.append(")"); //$NON-NLS-1$
    return builder;
  }

  private StringBuilder buildSimpleModelString(ISimpleSpecialsModel model) {
    StringBuilder builder = new StringBuilder();
    boolean defaultSpeed = model.getSpeed() == ISimpleSpecialsModel.DEFAULT_SPEED;
    boolean defaultDefense = model.getDefenseModifier() == ISimpleSpecialsModel.DEFAULT_DEFENSE_MODIFIER;
    boolean defaultTurnType = model.getTurnType() == TurnType.Tick;
    if (defaultSpeed && defaultDefense && defaultTurnType) {
      return builder;
    }
    builder.append(IMagicTooltipStringBuilder.Space);
    builder.append("("); //$NON-NLS-1$
    boolean dramaticAction = model.getTurnType() == TurnType.DramaticAction;
    boolean longTick = model.getTurnType() == TurnType.LongTick;
    if (dramaticAction) {
      builder.append(getResources().getString(getDramaticActionKey()));
    } else {
      if (!defaultSpeed || longTick || displayDefaultValues) {
        builder.append(buildSpeedString(model));
      }
    }
    if (!defaultDefense || displayDefaultValues) {
      builder.append(buildDefenseString(model, defaultSpeed, dramaticAction || longTick));
    }
    builder.append(")"); //$NON-NLS-1$
    return builder;
  }

  protected abstract StringBuilder buildSpeedString(ISimpleSpecialsModel model);

  @Override
  public String createTypeString(final ICharmTypeModel charmTypeModel) {
    final StringBuilder builder = new StringBuilder();
    CharmType charmType = charmTypeModel.getCharmType();
    builder.append(getResources().getString(charmType.getId()));
    if (charmTypeModel.hasSpecialsModel()) {
      charmType.accept(new ICharmTypeVisitor() {
        @Override
        public void visitExtraAction(CharmType visitedType) {
          // Nothing to do
        }

        @Override
        public void visitPermanent(CharmType visitedType) {
          // Nothing to do
        }

        @Override
        public void visitReflexive(CharmType visitedType) {
          builder.append(buildReflexiveModelString((IReflexiveSpecialsModel) charmTypeModel.getSpecialsModel()));
        }

        @Override
        public void visitSimple(CharmType visitedType) {
          builder.append(buildSimpleModelString((ISimpleSpecialsModel) charmTypeModel.getSpecialsModel()));
        }

        @Override
        public void visitSpecial(CharmType visitedType) {
          // Nothing to do
        }

        @Override
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
