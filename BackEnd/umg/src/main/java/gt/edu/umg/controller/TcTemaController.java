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

import gt.edu.umg.payload.ApiResponse;
import gt.edu.umg.config.AppProperties;
import gt.edu.umg.model.TcTema;
import gt.edu.umg.model.TcDetalle;
import gt.edu.umg.payload.ResponseResult;
import gt.edu.umg.repository.TcDetalleRepository;
import gt.edu.umg.repository.TcTemaRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/tema")
public class TcTemaController {

    private boolean showErrors;

    // @Autowired
    ApiResponse apiResponse = new ApiResponse();

    @Autowired
    public TcTemaController(AppProperties properties) {
        this.showErrors = (properties.getShowErrors() == 1);
    }
    @Autowired
    TcTemaRepository tcTemaRepository;

    @Autowired
    TcDetalleRepository tcDetalleRepository;

    @PostMapping("/add")
    public ApiResponse setTema(@Valid @RequestBody TcTema tcTema) {
        try {
            tcTemaRepository.save(tcTema);
            List<TcDetalle> detalle = tcTema.getDetalle();
            TcDetalle detll = null;
            for(TcDetalle det : detalle){
                det.setTcTema(tcTema);
                detll = tcDetalleRepository.save(det);
            }
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
    @PutMapping("/{idTema}")
    public ApiResponse updateTema(@PathVariable(value = "idTema") Long idTema, @Valid @RequestBody TcTema tcTema) {
        try {
            Optional<TcTema> found = tcTemaRepository.findByIdTema(idTema);
            TcTema tema = found.get();
            tema.setNombre(tcTema.getNombre());
            tema.setEstado(tcTema.getEstado());
            TcTema temaUpdated = tcTemaRepository.save(tema);
            List<TcTema> list = new ArrayList<>();
            list.add(temaUpdated);
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

    @PutMapping("/detalle/{idCorrelativo}")
    public ApiResponse updateDetalle(@PathVariable(value = "idCorrelativo") Long idCorrelativo, @Valid @RequestBody TcDetalle tcDetalle) {
        try {
            Optional<TcDetalle> found = tcDetalleRepository.findByIdCorrelativo(idCorrelativo); 
            TcDetalle detalle = found.get();
            detalle.setSubtema(tcDetalle.getSubtema());
            detalle.setEstado(tcDetalle.getEstado());
            detalle.setMes(tcDetalle.getMes());
            detalle.setFechaDesarrollar(tcDetalle.getFechaDesarrollar());
            detalle.setFechaRevision(tcDetalle.getFechaRevision());
            detalle.setPorcAvanceMensual(tcDetalle.getPorcAvanceMensual());
            detalle.setPorcAvanceSemanal(tcDetalle.getPorcAvanceSemanal());            
            TcDetalle temaUpdated = tcDetalleRepository.save(detalle);
            List<TcDetalle> list = new ArrayList<>();
            list.add(temaUpdated);
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
    @GetMapping("/{idTema}")
	public ApiResponse getOrder(@PathVariable(value = "idTema") Long idTema) {
		try {
            Optional<TcTema> item = tcTemaRepository.findById(idTema);
            TcTema tema = item.get();
            List<TcDetalle> detalle = tcDetalleRepository.findByTcTema(tema);
            for (TcDetalle det : detalle) {
                det.setTcTema(null);
            }
            tema.setDetalle(detalle);  
            List<TcTema> lista = new ArrayList<>();
            lista.add(tema);
            apiResponse.setData(lista);
            apiResponse.setStatus(ResponseResult.success.getValue());
            apiResponse.setMessage("Se obtuvo el  tema");
        
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

}