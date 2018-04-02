import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JToolTip;

class MyToolTip extends JLabel {
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
   public JToolTip createToolTip() {
      JToolTip jt = super.createToolTip();
      jt.setBackground(Color.WHITE);
      jt.updateUI();
      return jt;
   }
}