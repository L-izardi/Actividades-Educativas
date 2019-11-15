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

import gt.com.actividades.model.TcDepartamento;
import gt.com.actividades.repository.TcDepartamentoRepository;
import gt.com.actividades.utils.ApiResponse;
import gt.com.actividades.utils.ResponseResult;

@CrossOrigin(origins = "*", maxAge=3600)
@RestController
@RequestMapping("/departamento")
public class TcDepartamentoController{

    private boolean showErrors;

    @Autowired
    TcDepartamentoRepository tcDepartamentoRepository;

    ApiResponse apiResponse = new ApiResponse();

    @PostMapping("/add")
    public ApiResponse setProvider(@Valid @RequestBody TcDepartamento tcDepartamento) {
        try {
            tcDepartamentoRepository.save(tcDepartamento);
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
            List<?> list = tcDepartamentoRepository.findAll();
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
    
    @GetMapping("/{idDepartamento}")
    public ApiResponse findById(@PathVariable(value = "idDepartamento") Long idDepartamento) {
        try {
            Optional<TcDepartamento> found = tcDepartamentoRepository.findById(idDepartamento);
            List<TcDepartamento> list = new ArrayList<>();
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
    
    @PutMapping("/{idDepartamento}")
    public ApiResponse updateProvider(@PathVariable(value = "idDepartamento") Long idDepartamento, @Valid @RequestBody TcDepartamento tcDepartamento) {
        try {
            Optional<TcDepartamento> found = tcDepartamentoRepository.findByIdDepto(idDepartamento);
            TcDepartamento departamento = found.get();
            departamento.setNomDepto(tcDepartamento.getNomDepto());            
            TcDepartamento departamentoUpdated = tcDepartamentoRepository.save(departamento);
            List<TcDepartamento> list = new ArrayList<>();
            list.add(departamentoUpdated);
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