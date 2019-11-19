package gt.edu.umg.controller;

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

import gt.edu.umg.model.TcCentro;
import gt.edu.umg.repository.TcCentroRepository;
import gt.edu.umg.payload.ApiResponse;
import gt.edu.umg.config.AppProperties;
import gt.edu.umg.payload.ResponseResult;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/centro")
public class TcCentroController {
	
    private boolean showErrors;
    
    //@Autowired
    ApiResponse apiResponse = new ApiResponse();

    @Autowired
    public TcCentroController(AppProperties properties) {
       this.showErrors = (properties.getShowErrors() == 1);
    }
    @Autowired
    TcCentroRepository tcCentroRepository;
    
    @PostMapping("/add")
    public ApiResponse setCentro(@Valid @RequestBody TcCentro tcCentro) {
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
    @PutMapping("/{idCentro}")
    public ApiResponse updateCentro(@PathVariable(value = "idCentro") Long idCentro, @Valid @RequestBody TcCentro tcCentro) {
        try {
            Optional<TcCentro> found = tcCentroRepository.findByIdCentro(idCentro);
            TcCentro centro = found.get();
            centro.setNombre(tcCentro.getNombre());
            centro.setDireccion(tcCentro.getDireccion());
            centro.setCorreo(tcCentro.getCorreo());
            centro.setTelefono(tcCentro.getTelefono());
            centro.setTc_departamento(tcCentro.getTc_departamento());
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
    
    @GetMapping("/all")
	public ApiResponse getAllCentro() {
		try {
			List<?> lista = tcCentroRepository.findAll();
			apiResponse.setData(lista);
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
            Optional<TcCentro> found = tcCentroRepository.findByIdCentro(idCentro);
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
}
