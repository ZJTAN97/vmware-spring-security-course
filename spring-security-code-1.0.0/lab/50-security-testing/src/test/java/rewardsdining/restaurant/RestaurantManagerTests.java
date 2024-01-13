package rewardsdining.restaurant;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;

import common.money.Percentage;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import rewardsdining.IsAdmin;
import rewardsdining.WithMockCustomUser;

@SpringBootTest
public class RestaurantManagerTests {

    @Autowired
    private RestaurantManager restaurantManager;

    @Test
    @WithMockCustomUser
    public void testUserShouldNotBeAllowToUpdate() {
        var restaurant = restaurantManager.findById(1L).get();
        restaurant.setBenefitPercentage(new Percentage(0.2));
        assertThrows(AccessDeniedException.class, () -> restaurantManager.save(restaurant));
    }

    @Test
    @WithMockUser(username = "manager", roles = "MANAGER")
    public void testManagerShouldNotBeAllowToUpdate() {
        var restaurant = restaurantManager.findById(1L).get();
        restaurant.setBenefitPercentage(new Percentage(0.2));
        assertThrows(AccessDeniedException.class, () -> restaurantManager.save(restaurant));
    }

    @Test
    @IsAdmin
    public void testAdminShouldBeAllowedToUpdate() {
        var restaurant = restaurantManager.findById(1L).get();
        restaurant.setBenefitPercentage(new Percentage(0.2));
        assertDoesNotThrow(() -> restaurantManager.save(restaurant));
    }

    @Test
    @WithUserDetails("dollie")
    public void testOwnerShouldBeAllowToUpdate() {
        var restaurant = restaurantManager.findById(1L).get();
        restaurant.setBenefitPercentage(new Percentage(0.2));
        assertDoesNotThrow(() -> restaurantManager.save(restaurant));
    }

}
