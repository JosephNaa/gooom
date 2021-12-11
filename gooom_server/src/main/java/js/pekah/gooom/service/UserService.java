package js.pekah.gooom.service;

import js.pekah.gooom.model.User;
import js.pekah.gooom.payload.ApiResponse;
import js.pekah.gooom.payload.UserIdentityAvailability;
import js.pekah.gooom.payload.UserProfile;
import js.pekah.gooom.payload.UserSummary;
import js.pekah.gooom.security.UserPrincipal;

public interface UserService {

    UserSummary getCurrentUser(UserPrincipal currentUser);

    UserIdentityAvailability checkUsernameAvailability(String username);

    UserIdentityAvailability checkEmailAvailability(String email);

    UserProfile getUserProfile(String username);

    User addUser(User user);

    User updateUser(User newUser, String username, UserPrincipal currentUser);

    ApiResponse deleteUser(String username, UserPrincipal currentUser);

    ApiResponse giveAdmin(String username);

    ApiResponse removeAdmin(String username);

    UserProfile setOrUpdateInfo(UserPrincipal currentUser);

}
