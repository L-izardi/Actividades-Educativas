package gt.com.actividades.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gt.com.actividades.model.TcDepartamento;
import gt.com.actividades.repository.TcDepartamentoRepository;
import gt.com.actividades.utils.ApiResponse;
import gt.com.actividades.utils.AppProperty;
import gt.com.actividades.utils.ResponseResult;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/departamento")
public class TcDepartamentoController {
	
	private boolean showErrors;
	
	@Autowired
	ApiResponse apiResponse = new ApiResponse();
    
	@Autowired
	public TcDepartamentoController(AppProperty properties) {
		this.showErrors = (properties.getShowErrors() == 1);
	}
    
    @Autowired
    TcDepartamentoRepository tcDepartamentoRepository;
    
    @GetMapping("/all")
	public ApiResponse getAllProduct() {
		try {
			List<?> lista = tcDepartamentoRepository.findAll();
			apiResponse.setData(lista);
			apiResponse.setStatus(ResponseResult.success.getValue());
			apiResponse.setMessage(ResponseResult.success.getMessage());
		} catch (Exception e) {
			apiResponse.setStatus(ResponseResult.fail.getValue());
			if (showErrors) {
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
            Optional<TcDepartamento> found = tcDepartamentoRepository.findByIdDepto(idDepartamento);
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
	
}
