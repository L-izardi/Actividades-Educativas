import { TcCatedratico } from './tc-catedratico';
import { TcCurso } from './tc-curso';
import { TcCentro } from './tc-centro';

export class TcHeaders {
    
    public idCorrelativo:number;
    public tc_centro:TcCentro;
    public tc_curso:TcCurso;
    public tc_catedratico:TcCatedratico;
    public semestre:number;
    public seccion:String;
    public horario:String;
    public esetado:String;
        
}
