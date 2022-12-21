import com.intellij.ui.jcef.JBCefBrowser;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class DocumentationContentProvider {

    private String GetURL(String selection) {
        return "https://docs.oracle.com/en/java/javase/19/docs/api/search.html?q=" + selection;
    }

    public JPanel GetContent(String selection) {
        JPanel myToolWindowContent = new JPanel();

        var webView = new JBCefBrowser();
        webView.loadURL(GetURL(selection));

        myToolWindowContent.add(webView.getComponent());

        return myToolWindowContent;
    }
}
