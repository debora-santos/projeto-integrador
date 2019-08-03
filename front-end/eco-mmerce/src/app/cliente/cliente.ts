import {Dadospessoais} from './dadospessoais';
import {SignUpInfo} from '../auth/signup-info';

export class Cliente {
  id: number;
  dadosPessoais: Dadospessoais;
  enderecos: any;
  pedidos: any;
  user: SignUpInfo;
}
