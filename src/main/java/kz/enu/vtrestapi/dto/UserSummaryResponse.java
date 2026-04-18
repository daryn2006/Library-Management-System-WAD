package kz.enu.vtrestapi.dto;

public record UserSummaryResponse(Long id, String username, String fullName, String email, String role) {
}
