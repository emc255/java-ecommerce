package ECommerce.user.controller;

import ECommerce.user.dto.UserDTO;
import ECommerce.user.model.CreatePaymentResponse;
import ECommerce.user.model.User;
import ECommerce.user.model.UserItem;
import ECommerce.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = {"https://eeeshop.netlify.app/", "http://localhost:3000/"})
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    //PUBLIC
    //GET
    @GetMapping(path = "list")
    public List<User> getUserList() {
        return userService.getUserList();
    }

    @GetMapping(path = "sign-in")
    public void getUser(@RequestHeader String userName, @RequestHeader String password) {
    }

    @GetMapping(path = "isUsernameAvailable")
    public boolean isUsernameAvailable(@RequestParam String username) {
        return userService.isUsernameAvailable(username);
    }

    @GetMapping("/refresh/token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
//                Map<String, String> tokens = userService.refreshUserAccessToken(request, refresh_token);
                UserDTO userDTO = userService.refreshUserAccessToken(request, refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), userDTO);
            } catch (Exception e) {
                log.error("Error message  {}", e.getMessage());
                response.setHeader("error", e.getMessage());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new Hashtable<>();
                error.put("error_message", e.getMessage());
                UserDTO userDTO = new UserDTO("", "", e.getMessage(), "", true);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), userDTO);
            }
        } else {
            throw new RuntimeException("refresh token is missing");
        }
    }

    //POST
    @PostMapping(path = "createUser")
    public void createUser(@RequestHeader String firstname, @RequestHeader String username, @RequestHeader String password) {
        userService.createUser(firstname, username, password);
    }

    //PRIVATE
    //GET
    @GetMapping(path = "inventory")
    public List<UserItem> getUserItemList(HttpServletRequest request) {
        return userService.getUserItemList(request);
    }

    //POST
    @PostMapping(path = "item/add")
    public void addItemInUserItem(HttpServletRequest request, @RequestParam int itemId) {
        userService.addItemInUserItem(request, itemId);
    }

    @PostMapping("create-payment-intent")
    public CreatePaymentResponse createPaymentIntent(@RequestParam int value) throws StripeException {
        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency("usd")
                .setAmount(value * 100L)
                .build();
        PaymentIntent intent = PaymentIntent.create(createParams);
        return new CreatePaymentResponse(intent.getClientSecret());
    }

    //DELETE
    @DeleteMapping(path = "item/delete")
    public void deleteItemInUserItem(HttpServletRequest request, @RequestParam int itemId) {
        userService.deleteItemInUserItem(request, itemId);
    }

    @DeleteMapping(path = "/item/delete/{id}")
    public void deleteItemInUserItem(@PathVariable("id") @RequestParam int id) {
        userService.deleteItemInUserItem(id);
    }


}
