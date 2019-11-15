package gt.com.actividades.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gt.com.actividades.model.TcRol;
import gt.com.actividades.repository.TcRolRepository;
import gt.com.actividades.utils.ApiResponse;
import gt.com.actividades.utils.AppProperty;
import gt.com.actividades.utils.ResponseResult;

@RestController
@RequestMapping("/rol")
public class TcRolController {

    private boolean showErrors;

    @Autowired
    TcRolRepository tcRolRepository;

    ApiResponse apiResponse = new ApiResponse();

    @Autowired
    public TcRolController(AppProperty properties) {
        this.showErrors = (properties.getShowErrors() == 1);
    }

    @PostMapping("/add")
    public ApiResponse setRol(@Valid @RequestBody TcRol tcRol) {
        try {
            tcRolRepository.save(tcRol);
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

    @GetMapping("/all")
    public ApiResponse getAll() {
        try {
            List<?> list = tcRolRepository.findAll();
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

    @GetMapping("/{rolId}")
    public ApiResponse findById(@PathVariable(value = "rolId") Long rolId) {
        try {
            Optional<TcRol> found = tcRolRepository.findById(rolId);
            List<TcRol> list = new ArrayList<>();
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

    @PutMapping("/{rolId}")
    public ApiResponse updateRol(@PathVariable(value = "rolId") Long rolId, @Valid @RequestBody TcRol tcRol) {
        try {
            Optional<TcRol> found = tcRolRepository.findById(rolId);
            TcRol rol = found.get();
            rol.setRolDesc(tcRol.getRolDesc());
            rol.setEstado(tcRol.getEstado());
            TcRol rolUpdated = tcRolRepository.save(rol);
            List<TcRol> list = new ArrayList<>();
            list.add(rolUpdated);
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
