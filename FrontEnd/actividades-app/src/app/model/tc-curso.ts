import { TcCiclo } from './tc-ciclo';
import { TcFacultad } from './tc-facultad';

export class TcCurso {

    public idCurso:number;
    public nombreCurso:String
    public idCiclo:TcCiclo;
    public idFac:TcFacultad;
}
