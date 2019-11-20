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
 
import gt.edu.umg.model.TcFacultad;
import gt.edu.umg.repository.TcFacultadRepository;
import gt.edu.umg.payload.ApiResponse;
import gt.edu.umg.config.AppProperties;
import gt.edu.umg.payload.ResponseResult;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/facultad")
public class TcFacultadController {

    private boolean showErrors;

    // @Autowired
    ApiResponse apiResponse = new ApiResponse();

    @Autowired
    public TcFacultadController(AppProperties properties) {
        this.showErrors = (properties.getShowErrors() == 1);
    }
    @Autowired
    TcFacultadRepository tcFacultadRepository;
    
    @PostMapping("/add")
    public ApiResponse setFacultad(@Valid @RequestBody TcFacultad tcFacultad) {
        try {
            tcFacultadRepository.save(tcFacultad);
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
    @PutMapping("/{idFacultad}")
    public ApiResponse updateFacultad(@PathVariable(value = "idFacultad") Long idFacultad, @Valid @RequestBody TcFacultad tcFacultad) {
        try {
            Optional<TcFacultad> found = tcFacultadRepository.findByIdFacultad(idFacultad);
            TcFacultad facultad = found.get();
            facultad.setNombre(tcFacultad.getNombre());
            facultad.setTc_centro(tcFacultad.getTc_centro());
            TcFacultad facultadUpdated = tcFacultadRepository.save(facultad);
            List<TcFacultad> list = new ArrayList<>();
            list.add(facultadUpdated);
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
	public ApiResponse getAllFacultad() {
		try {
			List<?> lista = tcFacultadRepository.findAll();
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


    @GetMapping("/{idFacultad}")
    public ApiResponse findById(@PathVariable(value = "idFacultad") Long idFacultad) {
        try {
            Optional<TcFacultad> found = tcFacultadRepository.findByIdFacultad(idFacultad);
            List<TcFacultad> list = new ArrayList<>();
            list.add(found.get());
            apiResponse.setData(list);
            apiResponse.setStatus(ResponseResult.success.getValue());
            apiResponse.setSuccess(true);
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
