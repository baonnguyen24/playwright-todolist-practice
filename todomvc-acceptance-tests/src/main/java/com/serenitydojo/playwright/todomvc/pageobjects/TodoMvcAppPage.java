package com.serenitydojo.playwright.todomvc.pageobjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class TodoMvcAppPage {

    private final Page page;
    private final String baseUrl;

    public TodoMvcAppPage(Page page) {
        this.page = page;
        baseUrl = (StringUtils.isEmpty(System.getenv("APP_HOST_URL"))) ? "http://localhost:8080" : System.getenv("APP_HOST_URL");
    }

    public void open() {
        page.navigate(baseUrl);
    }

    // TODO: Add page object methods here

    public void addTodoItem(String item) {
        page.getByTestId("text-input").fill(item);
        page.getByTestId("text-input").press("Enter");
    }

    public void addTodoItems(String... items) {
        for(String item : items) {
            addTodoItem(item);
        }
    }

    public List<String> toDoItemDisplayed() {
        return page.getByTestId("todo-item").allTextContents();
    }

    public List<String> completedItems() {
        return page.locator(".completed").allTextContents();
    }

    public void deleteItem(String item) {
        Locator deletedItem = page.getByTestId("todo-item").filter(new Locator.FilterOptions().setHasText(item));
        deletedItem.hover();
        Locator deleteBtn = deletedItem.getByTestId("todo-item-button");
        deleteBtn.click();
    }

    public void deleteLastItem() {
        Locator deletedItem = page.getByTestId("todo-item").last();
        deletedItem.hover();
        Locator deleteBtn = deletedItem.getByTestId("todo-item-button");
        deleteBtn.click();
    }

    public void deleteFirstItem() {
        Locator deletedItem = page.getByTestId("todo-item").first();
        deletedItem.hover();
        Locator deleteBtn = deletedItem.getByTestId("todo-item-button");
        deleteBtn.click();
    }

    public void checkOffItem(String item) {
        Locator checkedItem = page.getByTestId("todo-item").filter(new Locator.FilterOptions().setHasText(item));
        checkedItem.getByTestId("todo-item-toggle").click();
    }

    public void selectFilter(String filterType) {
        Locator clickActiveFilter = page.locator(".filters");
        clickActiveFilter.getByText(filterType).click();
    }
}
