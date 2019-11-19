package gt.edu.umg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import gt.edu.umg.model.TcCatedratico;
import gt.edu.umg.repository.TcCatedraticoRepository;
import gt.edu.umg.payload.ApiResponse;
import gt.edu.umg.config.AppProperties;
import gt.edu.umg.payload.ResponseResult;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/catedratico")
public class TcCatedraticoController {
	
    private boolean showErrors;
    
    //@Autowired
    ApiResponse apiResponse = new ApiResponse();

    @Autowired
    public TcCatedraticoController(AppProperties properties) {
        this.showErrors = (properties.getShowErrors() == 1);
    }
    @Autowired
    TcCatedraticoRepository tcCatedraticoRepository;
    
    @GetMapping("/all")
	public ApiResponse getAllProduct() {
		try {
			List<?> lista = tcCatedraticoRepository.findAll();
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


    @GetMapping("/{idCatedratico}")
    public ApiResponse findById(@PathVariable(value = "idCatedratico") Long idCatedratico) {
        try {
            Optional<TcCatedratico> found = tcCatedraticoRepository.findByIdCatedratico(idCatedratico);
            List<TcCatedratico> list = new ArrayList<>();
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
