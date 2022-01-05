package com.adds.addon.api.auth;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasRole('ROLE_USER')")
@RestController
@RequestMapping("auth/photo/")
public class PhotoController {
}
