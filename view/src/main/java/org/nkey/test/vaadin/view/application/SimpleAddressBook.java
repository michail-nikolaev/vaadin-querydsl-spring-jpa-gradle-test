package org.nkey.test.vaadin.view.application;


import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.nkey.test.vaadin.domain.User;
import org.nkey.test.vaadin.view.application.repository.EntityItem;
import org.nkey.test.vaadin.view.application.repository.EntityItemContainer;
import org.nkey.test.vaadin.view.application.repository.user.UserManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import java.util.Arrays;


/**
 * @author m.nikolaev Date: 30.10.12 Time: 23:04
 */
@Scope("session")
@Controller
public class SimpleAddressBook extends Application {
    private static String[] fields = { "login", "password" };

    private Table contactList = new Table();
    private Form contactEditor = new Form();
    private HorizontalLayout bottomLeftCorner = new HorizontalLayout();
    private Button contactRemovalButton;
    private EntityItemContainer<EntityItem<User>> addressBookData;

    @Inject
    private UserManager userManager;

    @Override
    public void init() {
        addressBookData = new EntityItemContainer<>(userManager, Arrays.asList(fields), User.class);
        addressBookData.refresh();
        initLayout();
        initContactAddRemoveButtons();
        initAddressList();
        initFilteringControls();
    }

    private void initLayout() {
        HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
        setMainWindow(new Window("Address Book", splitPanel));
        VerticalLayout left = new VerticalLayout();
        left.setSizeFull();
        left.addComponent(contactList);
        contactList.setSizeFull();
        left.setExpandRatio(contactList, 1);
        splitPanel.addComponent(left);
        splitPanel.addComponent(contactEditor);
        contactEditor.setCaption("Contact details editor");
        contactEditor.setSizeFull();
        contactEditor.getLayout().setMargin(true);
        contactEditor.setImmediate(true);
        bottomLeftCorner.setWidth("100%");
        left.addComponent(bottomLeftCorner);
    }

    private void initContactAddRemoveButtons() {
        // New item button
        bottomLeftCorner.addComponent(new Button("+", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                // Add new contact "John Doe" as the first item
                Object id = ((IndexedContainer) contactList.getContainerDataSource()).addItemAt(0);
                contactList.getItem(id).getItemProperty("login").setValue("John");
                contactList.getItem(id).getItemProperty("password").setValue("Doe");

                // Select the newly added item and scroll to the item
                contactList.setValue(id);
                contactList.setCurrentPageFirstItemId(id);
            }
        }));

        // Remove item button
        contactRemovalButton = new Button("-", new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                contactList.removeItem(contactList.getValue());
                contactList.select(null);
            }
        });
        contactRemovalButton.setVisible(false);
        bottomLeftCorner.addComponent(contactRemovalButton);
    }

    private void initAddressList() {
        contactList.setContainerDataSource(addressBookData);
        //contactList.setVisibleColumns(fields);
        contactList.setSelectable(true);
        contactList.setImmediate(true);
        contactList.addListener(new Property.ValueChangeListener() {
            public void valueChange(ValueChangeEvent event) {
                Object id = contactList.getValue();
                contactEditor.setItemDataSource(id == null ? null : contactList.getItem(id));
                contactRemovalButton.setVisible(id != null);
            }
        });
    }

    private void initFilteringControls() {
        for (final String fieldName : fields) {
            final TextField sf = new TextField();
            bottomLeftCorner.addComponent(sf);
            sf.setWidth("100%");
            sf.setInputPrompt(fieldName);
            sf.setImmediate(true);
            bottomLeftCorner.setExpandRatio(sf, 1);
            sf.addListener(new Property.ValueChangeListener() {
                public void valueChange(ValueChangeEvent event) {

                    /*addressBookData.removeContainerFilters(pn);
                    if (sf.toString().length() > 0 && !pn.equals(sf.toString())) {
                        addressBookData.addContainerFilter(pn, sf.toString(), true, false);
                    }*/
                    getMainWindow().showNotification("" + addressBookData.size() + " matches found");
                }
            });
        }
    }
}
