import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.io.IOException;
import java.net.URL;

public class MyToolWindow {
    private JPanel myToolWindowContent;

    private String selection;
    
    private void InitializePanel()
    {

    }

    public MyToolWindow(ToolWindow toolWindow, String selection) {
        this.selection = selection;
        InitializePanel();
    }


    public JPanel getContent() {
        return myToolWindowContent;
    }

}
