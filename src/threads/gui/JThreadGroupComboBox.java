package threads.gui;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class JThreadGroupComboBox extends JComboBox<ThreadGroup>{

    public JThreadGroupComboBox(ComboBoxModel<ThreadGroup> aModel) {
        super(aModel);
        setup();
    }

    public JThreadGroupComboBox(ThreadGroup[] items) {
        super(items);
        setup();
    }

    public JThreadGroupComboBox(Vector<ThreadGroup> items) {
        super(items);
        setup();
    }

    public JThreadGroupComboBox() {
        setup();
    }


    private void setup() {
        JScrollPane threadGroupComboBoxScrollPane = new JScrollPane(this);
        threadGroupComboBoxScrollPane.setMaximumSize(new Dimension(200, 50));

        //This will print out the name of the thread group
        super.setRenderer(new ListCellRenderer<ThreadGroup>() {
            private DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

            @Override
            public Component getListCellRendererComponent(JList<? extends ThreadGroup> jList, ThreadGroup threadGroup, int i, boolean b, boolean b1) {
                JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(jList, threadGroup, i, b, b1);

                renderer.setText("View All");
                if (threadGroup != null) {
                    renderer.setText(threadGroup.getName());
                }
                return renderer;
            }
        });
    }


}
