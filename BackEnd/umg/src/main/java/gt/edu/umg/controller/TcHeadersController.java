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
 
import gt.edu.umg.model.TcHeaders;
import gt.edu.umg.repository.TcHeadersRepository;
import gt.edu.umg.payload.ApiResponse;
import gt.edu.umg.config.AppProperties;
import gt.edu.umg.payload.ResponseResult;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/headers")
public class TcHeadersController {

    private boolean showErrors;

    // @Autowired
    ApiResponse apiResponse = new ApiResponse();

    @Autowired
    public TcHeadersController(AppProperties properties) {
        this.showErrors = (properties.getShowErrors() == 1);
    }
    @Autowired
    TcHeadersRepository tcHeadersRepository;
    
    @PostMapping("/add")
    public ApiResponse setHeaders(@Valid @RequestBody TcHeaders tcHeaders) {
        try {
            tcHeadersRepository.save(tcHeaders);
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
    @PutMapping("/{idCorrelativo}")
    public ApiResponse updateHeaders(@PathVariable(value = "idCorrelativo") Long idCorrelativo, @Valid @RequestBody TcHeaders tcHeaders) {
        try {
            Optional<TcHeaders> found = tcHeadersRepository.findByIdCorrelativo(idCorrelativo);
            TcHeaders headers = found.get();
            headers.setTc_centro(tcHeaders.getTc_centro());
            headers.setTc_curso(tcHeaders.getTc_curso());
            headers.setTc_catedratico(tcHeaders.getTc_catedratico());
            headers.setSemestre(tcHeaders.getSemestre());
            headers.setSeccion(tcHeaders.getSeccion());
            headers.setHorario(tcHeaders.getHorario());
            headers.setEstado(tcHeaders.getEstado());
            TcHeaders headersUpdated = tcHeadersRepository.save(headers);
            List<TcHeaders> list = new ArrayList<>();
            list.add(headersUpdated);
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
	public ApiResponse getAllHeaders() {
		try {
			List<?> lista = tcHeadersRepository.findAll();
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


    @GetMapping("/{idCorrelativo}")
    public ApiResponse findById(@PathVariable(value = "idCorrelativo") Long idCorrelativo) {
        try {
            Optional<TcHeaders> found = tcHeadersRepository.findByIdCorrelativo(idCorrelativo);
            List<TcHeaders> list = new ArrayList<>();
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
