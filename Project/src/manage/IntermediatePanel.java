package manage;
import java.awt.*;
import javax.swing.*;

public class IntermediatePanel {
    public static JPanel display()
    {
        JPanel mainPanel = new JPanel(new BorderLayout());
        ChangePanel.parent = mainPanel;
        mainPanel.add(AddUser.display(), BorderLayout.CENTER);
        return mainPanel;
    }
}