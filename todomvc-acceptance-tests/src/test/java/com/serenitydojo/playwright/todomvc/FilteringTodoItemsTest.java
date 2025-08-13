package com.serenitydojo.playwright.todomvc;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.junit.UsePlaywright;
import com.serenitydojo.playwright.fixtures.ChromeHeadlessOptions;
import com.serenitydojo.playwright.todomvc.pageobjects.TodoMvcAppPage;
import io.qameta.allure.Feature;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Filtering todo items")
@UsePlaywright(ChromeHeadlessOptions.class)
@Feature("Filtering todo items")
class FilteringTodoItemsTest {

    TodoMvcAppPage todoMvcApp;

    @BeforeEach
    void openApp(Page page) {
        todoMvcApp = new TodoMvcAppPage(page);
        todoMvcApp.open();
    }

    @DisplayName("All items should be displayed by default")
    @Test
    void allItemsShouldBeDisplayedByDefault(Page page) {
        // TODO: Implement me
        // 1) Add "Feed the cat", "Walk the dog", "Buy some milk"
        // 2) Verify that the active filter is set to "All"
        todoMvcApp.addTodoItems("Feed the cat", "Walk the dog", "Buy some milk");

        String selectedFilter = page.locator(".filters .selected").textContent();
        Assertions.assertThat(selectedFilter).isEqualTo("All");
    }

    @DisplayName("Should be able to filter active items")
    @Test
    void shouldBeAbleToFilterByActiveItems(Page page) {
        // TODO: Implement me
        // 1) Add "Feed the cat", "Walk the dog", "Buy some milk"
        // 2) Complete "Walk the dog"
        // 3) Apply the "Active" filter
        // 4) Verify that only "Feed the cat" and "Buy some milk" are displayed
        todoMvcApp.addTodoItems("Feed the cat", "Walk the dog", "Buy some milk");

        todoMvcApp.checkOffItem("Walk the dog");

        todoMvcApp.selectFilter("Active");

        List<String> toDoItemDisplayed = todoMvcApp.toDoItemDisplayed();
        Assertions.assertThat(toDoItemDisplayed).containsExactly("Feed the cat", "Buy some milk");
    }

    @DisplayName("Should be able to filter completed items")
    @Test
    void shouldBeAbleToFilterByCompletedItems(Page page) {
        // TODO: Implement me
        // 1) Add "Feed the cat", "Walk the dog", "Buy some milk"
        // 2) Complete "Walk the dog"
        // 3) Apply the "Completed" filter
        // 4) Verify that only "Walk the dog" is displayed
        todoMvcApp.addTodoItems("Feed the cat", "Walk the dog", "Buy some milk");

        todoMvcApp.checkOffItem("Walk the dog");

        todoMvcApp.selectFilter("Completed");

        List<String> toDoItemDisplayed = todoMvcApp.toDoItemDisplayed();
        Assertions.assertThat(toDoItemDisplayed).containsExactly("Walk the dog");
    }

    @DisplayName("Should be able to revert to showing all items")
    @Test
    void shouldBeAbleToRevertToShowingAllItems(Page page) {
        // TODO: Implement me
        // 1) Add "Feed the cat", "Walk the dog", "Buy some milk"
        // 2) Complete "Walk the dog"
        // 3) Apply the "Completed" filter
        // 4) Apply the "All" filter
        // 5) Verify that all three items ("Feed the cat", "Walk the dog", "Buy some milk") are displayed
        todoMvcApp.addTodoItems("Feed the cat", "Walk the dog", "Buy some milk");

        todoMvcApp.checkOffItem("Walk the dog");

        todoMvcApp.selectFilter("Completed");

        todoMvcApp.selectFilter("All");

        List<String> toDoItemDisplayed = todoMvcApp.toDoItemDisplayed();
        Assertions.assertThat(toDoItemDisplayed).containsExactly("Feed the cat", "Walk the dog", "Buy some milk");
    }
}
