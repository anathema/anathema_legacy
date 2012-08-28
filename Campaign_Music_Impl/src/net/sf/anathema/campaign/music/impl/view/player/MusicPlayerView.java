package net.sf.anathema.campaign.music.impl.view.player;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.campaign.music.presenter.IMusicPlayerProperties;
import net.sf.anathema.campaign.music.presenter.selection.player.IMusicPlayerView;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;
import java.awt.GridLayout;

public class MusicPlayerView implements IMusicPlayerView {

  private final JButton playButton = new JButton();
  private final JButton stopButton = new JButton();
  private final JLabel totalTimeLabel = new JLabel();
  private final JLabel elapsedTimeLabel = new JLabel();
  private final JSlider slider = new JSlider(0, 100, 0);
  private JPanel content;

  public JComponent getContent(IMusicPlayerProperties properties) {
    if (content == null) {
      content = createContent(properties);
    }
    return content;
  }

  private JPanel createContent(IMusicPlayerProperties properties) {
    JPanel panel = new JPanel(new MigLayout(new LC().wrapAfter(1).fill()));
    panel.add(slider);
    panel.add(createTimeDisplay(properties), new CC().alignX("center"));
    panel.add(createControlDisplay(), new CC().alignX("center"));
    panel.setBorder(new TitledBorder(properties.getPlayerBorderString()));
    return panel;
  }

  private JComponent createTimeDisplay(IMusicPlayerProperties properties) {
    JPanel panel = new JPanel();
    panel.add(new JLabel(properties.getTimeString() + ":")); //$NON-NLS-1$
    panel.add(elapsedTimeLabel);
    panel.add(new JLabel("/")); //$NON-NLS-1$
    panel.add(totalTimeLabel);
    return panel;
  }

  private JComponent createControlDisplay() {
    JPanel panel = new JPanel(new GridLayout(1, 0));
    panel.add(playButton);
    panel.add(stopButton);
    return panel;
  }

  @Override
  public void setPlayAction(Action playAction) {
    playButton.setAction(playAction);
  }

  @Override
  public void setStopAction(Action action) {
    stopButton.setAction(action);
  }

  @Override
  public void addPositionChangeListener(ChangeListener listener) {
    slider.addChangeListener(listener);
  }

  @Override
  public int getCurrentPosition() {
    return slider.getValue();
  }

  @Override
  public void setMaximumPosition(int maximum, String time) {
    slider.setMaximum(maximum);
    totalTimeLabel.setText(time);
  }

  @Override
  public void setCurrentPosition(int position, String time) {
    if (slider.getValueIsAdjusting()) {
      return;
    }
    slider.setValue(position);
    elapsedTimeLabel.setText(time);
  }
}