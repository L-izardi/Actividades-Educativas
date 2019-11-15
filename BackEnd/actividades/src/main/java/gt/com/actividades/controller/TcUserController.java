package gt.com.actividades.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gt.com.actividades.jwt.JwtProvider;
import gt.com.actividades.model.TcUser;
import gt.com.actividades.model.User;
import gt.com.actividades.repository.TcUserRepository;
import gt.com.actividades.utils.ApiResponse;
import gt.com.actividades.utils.AppProperty;
import gt.com.actividades.utils.ResponseResult;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class TcUserController {

    private boolean showErrors;

    @Autowired
    TcUserRepository tcUserRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    ApiResponse apiResponse = new ApiResponse();

    @Autowired
    public TcUserController(AppProperty properties) {
        this.showErrors = (properties.getShowErrors() == 1);
    }

    @PostMapping("/login")
    public ApiResponse login(@Valid @RequestBody User user) {
        try {
            Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtProvider.generateJwtToken(authentication);
            Optional<TcUser> found = tcUserRepository.findByUsername(user.getUsername());
            TcUser tcUser = found.get();
            tcUser.setPassword("");
            List<TcUser> list = new ArrayList<>();
            list.add(tcUser);
            apiResponse.setData(list);
            apiResponse.setStatus(ResponseResult.success.getValue());
            apiResponse.setMessage(ResponseResult.success.getMessage());
            apiResponse.setSingleValue(token);
        } catch (Exception e) {
            apiResponse.setStatus(ResponseResult.fail.getValue());
            if (this.showErrors) {
                apiResponse.setMessage(e.getMessage());
            } else {
                apiResponse.setMessage(ResponseResult.fail.getMessage());
            }
        }
        return apiResponse;
    }

    @PostMapping("/add")
    public ApiResponse setUser(@Valid @RequestBody TcUser tcUser) {
        try {
            boolean bndContinue = true;
            if (tcUserRepository.existsByUsername(tcUser.getUsername())) {
                apiResponse.setStatus(ResponseResult.fail.getValue());
                apiResponse.setMessage("Username already exists");
                bndContinue = false;
            }
            if (bndContinue && tcUserRepository.existsByEmail(tcUser.getEmail())) {
                apiResponse.setStatus(ResponseResult.fail.getValue());
                apiResponse.setMessage("Email already exists");
                bndContinue = false;
            }
            if (bndContinue) {
                tcUser.setPassword(passwordEncoder.encode(tcUser.getPassword()));
                tcUserRepository.save(tcUser);
                apiResponse.setStatus(ResponseResult.success.getValue());
                apiResponse.setMessage(ResponseResult.success.getMessage());
            }
        } catch (Exception e) {
            apiResponse.setStatus(ResponseResult.fail.getValue());
            if (this.showErrors) {
                apiResponse.setMessage(e.getMessage());
            } else {
                apiResponse.setMessage(ResponseResult.fail.getMessage());
            }
        }
        return apiResponse;
    }

    @GetMapping("/all")
    public ApiResponse getAll() {
        try {
            List<?> list = tcUserRepository.findAll();
            apiResponse.setData(list);
            apiResponse.setStatus(ResponseResult.success.getValue());
            apiResponse.setMessage(ResponseResult.success.getMessage());
        } catch (Exception e) {
            apiResponse.setStatus(ResponseResult.fail.getValue());
            if (this.showErrors) {
                apiResponse.setMessage(e.getMessage());
            } else {
                apiResponse.setMessage(ResponseResult.fail.getMessage());
            }
        }
        return apiResponse;
    }

    @GetMapping("/{userId}")
    public ApiResponse findById(@PathVariable(value = "userId") Long userId) {
        try {
            Optional<TcUser> found = tcUserRepository.findById(userId);
            List<TcUser> list = new ArrayList<>();
            list.add(found.get());
            apiResponse.setData(list);
            apiResponse.setStatus(ResponseResult.success.getValue());
            apiResponse.setMessage(ResponseResult.success.getMessage());
        } catch (Exception e) {
            apiResponse.setStatus(ResponseResult.fail.getValue());
            if (this.showErrors) {
                apiResponse.setMessage(e.getMessage());
            } else {
                apiResponse.setMessage(ResponseResult.fail.getMessage());
            }
        }
        return apiResponse;
    }

    @PutMapping("/{userId}")
    public ApiResponse updateUser(@PathVariable(value = "userId") Long userId, @Valid @RequestBody TcUser tcUser) {
        try {
            Optional<TcUser> found = tcUserRepository.findById(userId);
            TcUser user = found.get();
            user.setFullname(tcUser.getFullname());
            user.setStatusId(tcUser.getStatusId());
            TcUser userUpdated = tcUserRepository.save(user);
            List<TcUser> list = new ArrayList<>();
            list.add(userUpdated);
            apiResponse.setData(list);
            apiResponse.setStatus(ResponseResult.success.getValue());
            apiResponse.setMessage(ResponseResult.success.getMessage());
        } catch (Exception e) {
            apiResponse.setStatus(ResponseResult.fail.getValue());
            if (this.showErrors) {
                apiResponse.setMessage(e.getMessage());
            } else {
                apiResponse.setMessage(ResponseResult.fail.getMessage());
            }
        }
        return apiResponse;
    }

}
