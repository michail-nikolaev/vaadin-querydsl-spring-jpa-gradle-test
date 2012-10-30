package org.nkey.test.vaadin.services.metadata;

/**
 * @author m.nikolaev Date: 31.10.12 Time: 2:32
 */
public class QueryMetaData {
    private String searchTerm;
    private String propertyName;

    public QueryMetaData(String propertyName, String searchTerm) {
        this.propertyName = propertyName;
        this.searchTerm = searchTerm;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
