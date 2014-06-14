package net.sf.anathema.hero.advance.overview.presenter;

import net.sf.anathema.framework.messaging.IMessaging;
import net.sf.anathema.hero.points.overview.SpendingModel;
import net.sf.anathema.lib.message.Message;
import net.sf.anathema.lib.message.MessageType;

import static java.text.MessageFormat.format;
import static net.sf.anathema.lib.message.MessageDuration.Permanent;
import static net.sf.anathema.lib.message.MessageType.NORMAL;
import static net.sf.anathema.lib.message.MessageType.WARNING;

class OverviewBonusPointsPresenter implements IOverviewSubPresenter {
  private SpendingModel model;
  private IMessaging messaging;

  public OverviewBonusPointsPresenter(SpendingModel model, IMessaging messaging) {
    this.model = model;
    this.messaging = messaging;
  }

  @Override
  public void update() {
    int spending = model.getValue();
    int allotment = model.getAllotment();
    String pattern;
    MessageType type;
    if (spending < allotment) {
      pattern = "You have spent {0} out of {1} Bonus Points.";
      type = NORMAL;
    } else {
      pattern = "You have overspent! {1} Bonus Points available, but {0} Bonus Points assigned.";
      type = WARNING;
    }
    messaging.addMessage(new Message(format(pattern, spending, allotment), type, Permanent));
  }
}