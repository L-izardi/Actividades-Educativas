package gt.com.actividades.utils;

public enum ResponseResult {

	fail("error"), success("ok"), warning("warning");
    private final String value;

    private ResponseResult(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public String getMessage() {
        String msg;
        switch(value.toLowerCase()) {
            case "ok":
                msg = "Proceso exitoso";
                break;
            case "warning":
                msg = "Hay errores que debe verificar";
                break;
            default:
                msg = "Error al procesar, intentelo de nuevo";
                break;
        }
        return msg;
    }
}
