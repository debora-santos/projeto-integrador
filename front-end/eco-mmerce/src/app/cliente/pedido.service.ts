import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TokenStorageService} from '../auth/token-storage.service';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {

  private baseUrl = 'http://localhost:8080/api/v1/pedidos';
  private header = {Authorization: `Bearer ${this.token.getToken()}`};

  constructor(private http: HttpClient, private token: TokenStorageService) { }

  getPedido(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`, {headers: this.header});
  }

  // tslint:disable-next-line:ban-types
  createPedido(endereco: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, endereco, {headers: this.header});
  }

  // tslint:disable-next-line:ban-types
  updatePedido(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value, {headers: this.header});
  }

  deletePedido(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, {responseType: 'text', headers: this.header});
  }

  getPedidos(): Observable<any> {
    return this.http.get(`${this.baseUrl}`, {headers: this.header});
  }
}
