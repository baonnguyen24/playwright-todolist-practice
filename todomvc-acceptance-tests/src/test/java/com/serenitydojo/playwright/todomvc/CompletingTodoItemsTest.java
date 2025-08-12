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

@DisplayName("Completing todo items to the list")
@UsePlaywright(ChromeHeadlessOptions.class)
@Feature("Completing todo items to the list")
class CompletingTodoItemsTest {

    TodoMvcAppPage todoMvcApp;

    @BeforeEach
    void openApp(Page page) {
        todoMvcApp = new TodoMvcAppPage(page);

        todoMvcApp.open();
    }

    @DisplayName("Completed items should be marked as completed")
    @Test
    void completedItemsShouldBeMarkedAsCompleted(Page page) {
        // TODO: Implement me
        // 1) Add "Feed the cat", "Walk the dog", "Buy some milk"
        // 2) Complete "Feed the cat"
        // 3) Check that "Feed the cat" is shown as completed
        page.getByTestId("text-input").fill("Feed the cat");
        page.getByTestId("text-input").press("Enter");
        page.getByTestId("text-input").fill("Walk the dog");
        page.getByTestId("text-input").press("Enter");
        page.getByTestId("text-input").fill("Buy some milk");
        page.getByTestId("text-input").press("Enter");

        Locator item = page.getByTestId("todo-item").filter(new Locator.FilterOptions().setHasText("Feed the cat"));
        item.getByTestId("todo-item-toggle").click();

        List<String> completedItems = page.locator(".completed").allTextContents();
        Assertions.assertThat(completedItems).containsExactly("Feed the cat");
    }

    @DisplayName("Completing an item should update the number of items left count")
    @Test
    void shouldUpdateNumberOfItemsLeftCount(Page page) {
        // TODO: Implement me
        // 1) Add "Feed the cat", "Walk the dog", "Buy some milk"
        // 2) Complete "Feed the cat"
        // 3) Verify the todo count shows "2 items left!"
        page.getByTestId("text-input").fill("Feed the cat");
        page.getByTestId("text-input").press("Enter");
        page.getByTestId("text-input").fill("Walk the dog");
        page.getByTestId("text-input").press("Enter");
        page.getByTestId("text-input").fill("Buy some milk");
        page.getByTestId("text-input").press("Enter");

        Locator item = page.getByTestId("todo-item").filter(new Locator.FilterOptions().setHasText("Feed the cat"));
        item.getByTestId("todo-item-toggle").click();

        PlaywrightAssertions.assertThat(page.locator(".todo-count")).hasText("2 items left!");
    }

    @DisplayName("Should be able to clear completed items")
    @Test
    void shouldBeAbleToClearCompletedItems(Page page) {
        // TODO: Implement me
        // 1) Add "Feed the cat", "Walk the dog", "Buy some milk"
        // 2) Complete "Walk the dog"
        // 3) Clear the completed items
        // 4) Verify that the remaining items are "Feed the cat" and "Buy some milk"
        page.getByTestId("text-input").fill("Feed the cat");
        page.getByTestId("text-input").press("Enter");
        page.getByTestId("text-input").fill("Walk the dog");
        page.getByTestId("text-input").press("Enter");
        page.getByTestId("text-input").fill("Buy some milk");
        page.getByTestId("text-input").press("Enter");

        Locator item = page.getByTestId("todo-item").filter(new Locator.FilterOptions().setHasText("Walk the dog"));
        item.getByTestId("todo-item-toggle").click();

        Locator clearComplete = page.locator(".clear-completed");
        clearComplete.click();

        List<String> toDoItems = page.getByTestId("todo-item").allTextContents();
        Assertions.assertThat(toDoItems).containsExactly("Feed the cat", "Buy some milk");
    }
}
