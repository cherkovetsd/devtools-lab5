import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.wm.RegisterToolWindowTask;
import com.intellij.openapi.wm.RegisterToolWindowTaskBuilder;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.toolWindow.RegisterToolWindowTaskProvider;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.codehaus.groovy.antlr.java.JavaTokenTypes;
import org.jetbrains.annotations.NotNull;


import com.intellij.codeInspection.*;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.ui.DocumentAdapter;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;


public class LookupDocumentationAction extends AnAction {

    private DocumentationContentProvider documentationContentProvider;

    public LookupDocumentationAction() {
        super();
        documentationContentProvider = new DocumentationContentProvider();
        System.out.println("CREATED");
        System.out.println("!!!!!!");
        System.out.println("!!!!!!");
    }

    @Override
    public void update(@NotNull AnActionEvent event) {
        // Using the event, evaluate the context,
        // and enable or disable the action.
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {

        try {
            var project = event.getData(CommonDataKeys.PROJECT);

            Editor editor = event.getRequiredData(CommonDataKeys.EDITOR);
            Document document = editor.getDocument();

            Caret primaryCaret = editor.getCaretModel().getPrimaryCaret();
            int start = primaryCaret.getSelectionStart();
            int end = primaryCaret.getSelectionEnd();

            var selection = document.getText(new TextRange(start, end));

            var toolWindowManager = ToolWindowManager.getInstance(project);

            var toolWindow = toolWindowManager.getToolWindow("Documentation");

            ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
            Content content = contentFactory.createContent(documentationContentProvider.GetContent(selection), "", false);
            content.setDisplayName(selection);
            toolWindow.getContentManager().addContent(content);
            toolWindow.show();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Override getActionUpdateThread() when you target 2022.3 or later!

}