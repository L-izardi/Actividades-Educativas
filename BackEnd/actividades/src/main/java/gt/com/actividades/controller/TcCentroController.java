package gt.com.actividades.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gt.com.actividades.model.TcCentro;
import gt.com.actividades.repository.TcCentroRepository;
import gt.com.actividades.utils.ApiResponse;
import gt.com.actividades.utils.AppProperty;
import gt.com.actividades.utils.ResponseResult;

@CrossOrigin(origins = "*", maxAge=3600)
@RestController
@RequestMapping("/centro")
public class TcCentroController{

    ApiResponse apiResponse = new ApiResponse();
    private boolean showErrors;

    @Autowired
    TcCentroRepository tcCentroRepository;

    @Autowired
    public TcCentroController (AppProperty properties) {
        this.showErrors = (properties.getShowErrors() == 1);
    }

    @PostMapping("/add")
    public ApiResponse setProvider(@Valid @RequestBody TcCentro tcCentro) {
        try {
            tcCentroRepository.save(tcCentro);
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
            List<?> list = tcCentroRepository.findAll();
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
    
    @GetMapping("/{idCentro}")
    public ApiResponse findById(@PathVariable(value = "idCentro") Long idCentro) {
        try {
            Optional<TcCentro> found = tcCentroRepository.findById(idCentro);
            List<TcCentro> list = new ArrayList<>();
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
    
    @PutMapping("/{idCentro}")
    public ApiResponse updateProvider(@PathVariable(value = "idCentro") Long idCentro, @Valid @RequestBody TcCentro tcCentro) {
        try {
            Optional<TcCentro> found = tcCentroRepository.findByIdCentro(idCentro);
            TcCentro centro = found.get();
            centro.setNombre(tcCentro.getNombre());
            centro.setCorreo(tcCentro.getCorreo());
            centro.setDireccion(tcCentro.getDireccion());
            
            TcCentro centroUpdated = tcCentroRepository.save(centro);
            List<TcCentro> list = new ArrayList<>();
            list.add(centroUpdated);
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